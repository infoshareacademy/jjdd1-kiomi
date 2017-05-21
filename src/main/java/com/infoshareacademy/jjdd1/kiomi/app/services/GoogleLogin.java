package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.AdministratorEmails;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.GoogleUser;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by arek50 on 2017-05-02.
 */
@WebServlet(urlPatterns = "/googlelogin")
public class GoogleLogin extends HttpServlet {
    @Inject
    SessionData sessionData;

    final String CLIENT_ID = "474401942226-bqn8a5k7hojtujm2l6v9ie3m2a6ob2qo.apps.googleusercontent.com";
    final String CLIENT_SECRET = "ShKL7hQ1gJCYI_Eq9Sj9rH-y";
    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    private OAuth20Service service = new ServiceBuilder()
            .apiKey(CLIENT_ID)
            .apiSecret(CLIENT_SECRET)
            .scope("profile")
            .scope("email")
            .callback("http://localhost:8080/googlelogin")
            .build(GoogleApi20.instance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (null != req.getParameter("error")) {
            req.setAttribute("error", req.getParameter("error"));
            return;
        }

//refresh_token or redirect
        final String code = req.getParameter("code");
        if (null != code) {
            OAuth2AccessToken accessToken = null;

            try {
                accessToken = service.getAccessToken(code);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
            service.signRequest(accessToken, request);

            Response response = null;
            try {
                response = service.execute(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (response.getCode() != 200) {
                req.setAttribute("error", "Brak połączenia z api google");
            } else {
                String googleJson = response.getBody();
                Gson gson = new Gson();
                GoogleUser googleUser = gson.fromJson(googleJson, GoogleUser.class);

                AdministratorEmails administratorEmails=new AdministratorEmails();
                if(administratorEmails.isAdministrator(googleUser.getEmail())==1) {
                    sessionData.logUser(googleUser.getGiven_name(), googleUser.getFamily_name(), googleUser.getPicture(), googleUser.getEmail());
                    resp.sendRedirect("http://localhost:8080/googlelogin");
                } else {
                    req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
                }
            }
        }
        Map<String, String> sessionUser = new HashMap<>();
        sessionUser.put("given_name", sessionData.getFirstname());
        sessionUser.put("family_name", sessionData.getLastname());
        sessionUser.put("picture", sessionData.getPicture());
        sessionUser.put("email", sessionData.getEmail());
        req.setAttribute("oauth", sessionUser);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/GoogleLogin.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("login").equals("1")) {
            final Map<String, String> additionalParams = new HashMap<>();
            additionalParams.put("access_type", "offline");
            additionalParams.put("prompt", "consent");
            resp.sendRedirect(service.getAuthorizationUrl(additionalParams));
            req.setAttribute("oauth", "wysyłam żądanie do google...");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/GoogleLogin.jsp");
            dispatcher.forward(req, resp);
        }
    }
}

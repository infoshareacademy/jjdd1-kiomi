package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by arek50 on 2017-05-02.
 */
@WebServlet(urlPatterns = "/oauth2callback")
public class oauth2callback extends HttpServlet {

    final String CLIENT_ID = "474401942226-bqn8a5k7hojtujm2l6v9ie3m2a6ob2qo.apps.googleusercontent.com";
    final String CLIENT_SECRET = "ShKL7hQ1gJCYI_Eq9Sj9rH-y";
    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        OAuth20Service service = new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .scope("profile")
                .scope("email")
                .callback("http://localhost:8080/oauth2callback")
                .build(GoogleApi20.instance());
        final String code = req.getParameter("code");

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

        if (response.getCode()!=200) {
            req.setAttribute("oauth", "Brak połączenia z api google");
        } else {
            req.setAttribute("oauth", response.getBody());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/oauth2callback.jsp");
        dispatcher.forward(req, resp);
    }

}

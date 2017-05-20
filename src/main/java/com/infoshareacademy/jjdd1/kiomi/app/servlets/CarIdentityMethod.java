package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.services.SessionData;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arek50 on 2017-05-04.
 */
@WebServlet(urlPatterns = "/caridentitymethod")
public class CarIdentityMethod extends HttpServlet{

    @Inject
    SessionData sessionData;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (null != req.getParameter("error")) {
            req.setAttribute("error", req.getParameter("error"));
            return;
        }
        if(sessionData.isLogged()==false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("/googlelogin");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/carIdentityMethod.jsp");
        dispatcher.forward(req, resp);

    }
}

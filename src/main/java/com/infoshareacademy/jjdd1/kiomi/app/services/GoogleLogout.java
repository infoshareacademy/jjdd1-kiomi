package com.infoshareacademy.jjdd1.kiomi.app.services;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arek50 on 2017-05-03.
 */
@WebServlet("/logout")
public class GoogleLogout extends HttpServlet {
    @Inject
    SessionData sessionData;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sessionData.logout();
        resp.sendRedirect("/googlelogin");

    }
}

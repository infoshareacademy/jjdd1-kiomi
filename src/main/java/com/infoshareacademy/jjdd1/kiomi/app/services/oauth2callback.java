package com.infoshareacademy.jjdd1.kiomi.app.services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arek50 on 2017-05-02.
 */
@WebServlet(urlPatterns = "/oauth2callback")
public class oauth2callback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
req.getParameter("x");
        System.out.println("x");
    }

}

package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.BrandsCache;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader2;
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
 * Created by arek50 on 2017-05-05.
 */
@WebServlet(urlPatterns = "/setadminmemebers")
public class SetMembers extends HttpServlet {
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    SessionData sessionData;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (sessionData.isLogged() == false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("http://localhost:8080/googlelogin");
        }

        req.setCharacterEncoding("UTF-8");
        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carTypeName = sessionData.getCar().getCarType().getName();

        req.setAttribute("brand", brandName);
        req.setAttribute("model", modelName);
        req.setAttribute("carType", carTypeName);
        req.setAttribute("sessionUserName", sessionData.getFirstname());
//            req.setAttribute("errorMessage", errorMessage);

        RequestDispatcher dispatcher = req.getRequestDispatcher("SetAdminMembers.jsp");
        dispatcher.forward(req, resp);

    }
}

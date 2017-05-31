package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.google.common.base.Strings;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.BrandsCache;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader2;
import com.infoshareacademy.jjdd1.kiomi.app.services.SessionData;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.promotedBrands.PromotedBrandPersistence;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.promotedBrands.PromotedBrands;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by arek50 on 2017-05-05.
 */
@WebServlet(urlPatterns = "/promotedbrand")
public class SetPromotedBrands extends HttpServlet {
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    SessionData sessionData;

    PromotedBrandPersistence brandPersist = new PromotedBrandPersistence();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (sessionData.isLogged() == false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("/googlelogin");
        }

        req.setCharacterEncoding("UTF-8");
        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carTypeName = sessionData.getCar().getCarType().getName();
        req.setAttribute("isAdmin", sessionData.isAdmin());

        req.setAttribute("brand", brandName);
        req.setAttribute("model", modelName);
        req.setAttribute("carType", carTypeName);
        req.setAttribute("sessionUserName", sessionData.getFirstname());
//            req.setAttribute("errorMessage", errorMessage);

        List<PromotedBrands> promotedBrandsList = brandPersist.getAllBrands();
        req.setAttribute("promotedBrandsList", promotedBrandsList);


        RequestDispatcher dispatcher = req.getRequestDispatcher("setPromotedBrands.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean promotedBrandToAddIsempty;
        boolean promotedBrandToRemoveIsempty;

        String formType = req.getParameter("type");
        String promotedBrandToAdd = req.getParameter("brand");
        String promotedBrandToRemove = req.getParameter("id");
        promotedBrandToAddIsempty = (Strings.isNullOrEmpty(promotedBrandToAdd));
        promotedBrandToRemoveIsempty = (Strings.isNullOrEmpty(promotedBrandToRemove));
        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carTypeName = sessionData.getCar().getCarType().getName();

        req.setAttribute("sessionUserName", sessionData.getFirstname());
        req.setAttribute("isAdmin", sessionData.isAdmin());
        req.setAttribute("brand", brandName);
        req.setAttribute("model", modelName);
        req.setAttribute("carType", carTypeName);




        if(formType.equals("add") && !promotedBrandToAddIsempty) {
            brandPersist.addBrand(promotedBrandToAdd);

        }

        if (formType.equals("delete") && !promotedBrandToRemoveIsempty) {
            brandPersist.removeBrand(promotedBrandToRemove);
        }

        List<PromotedBrands> promotedBrandsList = brandPersist.getAllBrands();
        req.setAttribute("promotedBrandsList", promotedBrandsList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/setPromotedBrands.jsp");
        dispatcher.forward(req, resp);

    }
}

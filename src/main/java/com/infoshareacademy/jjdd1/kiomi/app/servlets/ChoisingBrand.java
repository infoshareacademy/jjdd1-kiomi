package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
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
import java.util.List;

/**
 * Created by arek50 on 2017-05-05.
 */
@WebServlet(urlPatterns = "/choisingbrand")
public class ChoisingBrand extends HttpServlet {
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    SessionData sessionData;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(sessionData.isLogged()==false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("/googlelogin");
        }
        sessionData.setCar(null);
        List<Brand> brands = brandsCache.getBrandsList();
        req.setCharacterEncoding("UTF-8");
//        Car car = new Car();

        req.setAttribute("brandList", brands);
        req.setAttribute("action", "choisingmodel");
        if (brands.size() == 0) {
            String errorMessage = ("Nie znaleziono listy marek samochodu. <a href='choisingbrand'>Wyszukaj ponownie</a>.");

            req.setAttribute("errorMessage", errorMessage);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingBrand.jsp");
        dispatcher.forward(req, resp);

    }
}

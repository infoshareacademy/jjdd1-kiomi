package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
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
import java.util.stream.Collectors;

/**
 * Created by arek50 on 2017-05-05.
 */
@WebServlet(urlPatterns = "/choisingcartype")
public class ChoisingCarType extends HttpServlet {
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    SessionData sessionData;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/caridentitymethod");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(sessionData.isLogged()==false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("/googlelogin");
        }
        try {
            Brand selectedBrand = sessionData.getCar().getBrand();
        } catch (NullPointerException e) {
            resp.sendRedirect("/choisingbrand");
        }

        req.setCharacterEncoding("UTF-8");
        Car car = new Car();

        Brand selectedBrand = sessionData.getCar().getBrand();
        String url = "http://infoshareacademycom.2find.ru" + selectedBrand.getLink() + "?lang=polish";
        List<Model> modelsList = carsDataLoader2.getModelsListBylink(url);
        List<Model> selectedModel = modelsList.stream().filter(b -> b.getId().equals(req.getParameter("model"))).collect(Collectors.toList());
        car.setBrand(sessionData.getCar().getBrand());
        car.setModel(selectedModel.get(0));
        url = "http://infoshareacademycom.2find.ru" + car.getModel().getLink() + "?lang=polish";
        List<Type> typesList = carsDataLoader2.getTypesListByLink(url);

        if (typesList.size() == 0) {
            String errorMessage = ("Nie znaleziono listy z typami silnika dla samochodu. <a href='choisingbrand'>Wyszukaj ponownie</a>.");
            req.setAttribute("errorMessage", errorMessage);
        }

        String brandName = sessionData.getCar().getBrand().getName();
        String brandId = sessionData.getCar().getBrand().getId();
        String modelName = car.getModel().getName();

        req.setAttribute("brand", brandName);
        req.setAttribute("brandId", brandId);
        req.setAttribute("model", modelName);
        req.setAttribute("typeList", typesList);
        req.setAttribute("action", "index");

        sessionData.setCar(car);
        RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingCarType.jsp");
        dispatcher.forward(req, resp);

    }
}

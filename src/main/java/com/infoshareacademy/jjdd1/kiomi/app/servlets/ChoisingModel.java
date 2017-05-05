package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.BrandsCache;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Car;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
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
@WebServlet(urlPatterns = "/choisingmodel")
public class ChoisingModel extends HttpServlet {
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    SessionData sessionData;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Brand> brands = brandsCache.getBrandsList();
        req.setCharacterEncoding("UTF-8");
        Car mycar = new Car();

        List<Brand> selectedBrand=brands.stream().filter(b->b.getId().equals(req.getParameter("brand"))).collect(Collectors.toList());
        mycar.setBrand(selectedBrand.get(0));

        String url = "http://infoshareacademycom.2find.ru" + selectedBrand.get(0).getLink() + "?lang=polish";
        List<Model> modelsList = carsDataLoader2.getModelsListBylink(url);

        req.setAttribute("brandList", brands);
        if (modelsList.size() == 0) {
            String errorMessage = ("Nie znaleziono listy modeli samochodu. Wybierz z listy.");
            req.setAttribute("errorMessage", errorMessage);
        }


        String brandName = mycar.getBrand().getName();
        String brandId = mycar.getBrand().getId();

        req.setAttribute("brand", brandName);
        req.setAttribute("brandId", brandId);
        req.setAttribute("modelList", modelsList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingModel.jsp");
        dispatcher.forward(req, resp);

    }
}

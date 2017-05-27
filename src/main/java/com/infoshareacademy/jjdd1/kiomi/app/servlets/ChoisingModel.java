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
import java.io.PrintWriter;
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
        resp.sendRedirect("/caridentitymethod");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(sessionData.isLogged()==false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("/googlelogin");
        }
        try{
        List<Brand> brands = brandsCache.getBrandsList();
        req.setCharacterEncoding("UTF-8");
        Car car = new Car();

        List<Brand> selectedBrand = brands.stream().filter(b -> b.getId().equals(req.getParameter("brand"))).collect(Collectors.toList());
        car.setBrand(selectedBrand.get(0));

        String url = "http://infoshareacademycom.2find.ru" + selectedBrand.get(0).getLink() + "?lang=polish";
        List<Model> modelsList = carsDataLoader2.getModelsListBylink(url);

        req.setAttribute("brandList", brands);
        req.setAttribute("action", "choisingcartype");
        if (modelsList.size() == 0) {
            String errorMessage = ("Nie znaleziono listy modeli samochodu. <a href='choisingbrand'>Wyszukaj ponownie</a>.");
            req.setAttribute("errorMessage", errorMessage);
        }


        String brandName = car.getBrand().getName();
        String brandId = car.getBrand().getId();

        req.setAttribute("brand", brandName);
        req.setAttribute("brandId", brandId);
        req.setAttribute("modelList", modelsList);

        sessionData.setCar(car);
        RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingModel.jsp");
        dispatcher.forward(req, resp);
        } catch (IOException e) {
            System.out.println("Nie ma pliku na serwerze: " + e.getMessage());
            PrintWriter writer = resp.getWriter();
            writer.append("<b>Nie ma pliku na serwerze<br>" + e.getMessage() + "</b>");
            writer.flush();
        }
    }
}

package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.BrandsCache;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Car;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Part;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.PartCategory;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Type;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by arek50 on 2017-05-05.
 */
@WebServlet(urlPatterns = "/choisingpartcategory")
public class ChoisingPartCategory extends HttpServlet {
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
            resp.sendRedirect("http://localhost:8080/googlelogin");
        }
        try {
            Type selectedCarType = sessionData.getCar().getCarType();
        } catch (NullPointerException e) {
            resp.sendRedirect("http://localhost:8080/choisingbrand");
        }
//Dalej normalna obsługa strony na kliknięcia z servletem

        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carTypeName = sessionData.getCar().getCarType().getName();

        req.setAttribute("brand", brandName);
        req.setAttribute("carType", carTypeName);
        req.setAttribute("model" , modelName);

////zmiennna dla kat=idkat
//        Map<String, String[]> parameters = req.getParameterMap();
//
//        String url = "http://infoshareacademycom.2find.ru/api/v2/find/";
//        String url2="?lang=polish";
//
//        List<PartCategory> partCategories = new ArrayList<>();
//        List<Part> part = new ArrayList<>();
//        if (kat.equals("")) {
//            if (!TYP.equals("")) {
//                partCategories = carsDataLoader2.getPartCategoryListByLink(url+IDzTYPU+url2);
//            }
//        } else {
//            url += "/" + kat[kat.length - 1];
//
//            part = carsDataLoader2.getPartListByLink(url + "/stock"+url2);
//            String[] s = Optional.ofNullable(parameters.get("stock")).orElse(new String[]{""});
//            if (s[0].equals("")) {
//                partCategories = carsDataLoader2.getPartCategoryListByLink(url+url2);
//            }
//        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("index1.jsp");
        dispatcher.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(sessionData.isLogged()==false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("http://localhost:8080/googlelogin");
        }
        try {
            Model selectedModel = sessionData.getCar().getModel();
        } catch (NullPointerException e) {
            resp.sendRedirect("http://localhost:8080/choisingbrand");
        }

        req.setCharacterEncoding("UTF-8");
        Car car = new Car();

        Model selectedModel= sessionData.getCar().getModel();
        String url = "http://infoshareacademycom.2find.ru" + selectedModel.getLink() + "?lang=polish";
        List<Type> carTypeList = carsDataLoader2.getTypesListByLink(url);
        List<Type> selectedCarType = carTypeList.stream().filter(b -> b.getId().equals(req.getParameter("type"))).collect(Collectors.toList());

        car.setCarType(selectedCarType.get(0));
        car.setBrand(sessionData.getCar().getBrand());
        car.setModel(sessionData.getCar().getModel());
        sessionData.setCar(car);

        resp.sendRedirect("http://localhost:8080/choisingpartcategory");
    }
}

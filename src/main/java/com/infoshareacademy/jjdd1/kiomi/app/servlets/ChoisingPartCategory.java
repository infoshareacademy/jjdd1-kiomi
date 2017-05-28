package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader2;
import com.infoshareacademy.jjdd1.kiomi.app.services.PromotedBrandsLoader;
import com.infoshareacademy.jjdd1.kiomi.app.services.SessionData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

@WebServlet({"/index",""})

public class ChoisingPartCategory extends HttpServlet {

    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    SessionData sessionData;

    private static final Logger LOGGER = LogManager.getLogger(ChoisingPartCategory.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (sessionData.isLogged() == false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("/googlelogin");
        }

        try {
            Type selectedCarType = sessionData.getCar().getCarType();
        } catch (NullPointerException e) {
            resp.sendRedirect("/choisingbrand");
        }

        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carTypeName = sessionData.getCar().getCarType().getName();

        req.setAttribute("brand", brandName);
        req.setAttribute("model", modelName);
        req.setAttribute("carType", carTypeName);
        req.setAttribute("sessionUserName", sessionData.getFirstname());
        req.setAttribute("isAdmin", sessionData.isAdmin());


        String url = "http://infoshareacademycom.2find.ru/api/v2/find/";
        String url2 = "?lang=polish";
        String brandId = sessionData.getCar().getBrand().getId();
        String modelId = sessionData.getCar().getModel().getId();
        String carTypeId = sessionData.getCar().getCarType().getId();
//        String partCategoryId = sessionData.getPartCategory();
        Map<String, String[]> parameters = req.getParameterMap();
        String[] category = Optional.ofNullable(parameters.get("cat")).orElse(new String[]{""});

        List<PartCategory> partCategories;

        List<Part> part = new ArrayList<>();
        if (!category[0].equals("")) {


            String parturl=url + brandId + "/" + modelId + "/" + carTypeId + "/" + category[0] + "/stock";
            part = carsDataLoader2.getPartListByLink(parturl + url2);
            LOGGER.debug("Part url samochodu to: {}", parturl + url2);
            String partCategoriesUrl=url + brandId + "/" + modelId + "/" + carTypeId + "/" + category[0];
            partCategories = carsDataLoader2.getPartCategoryListByLink(partCategoriesUrl + url2);
            LOGGER.debug("PartCategory url samochodu to: {}", partCategoriesUrl + url2);

        } else {
            String partCategoriesUrl=url + brandId + "/" + modelId + "/" + carTypeId;
            partCategories = carsDataLoader2.getPartCategoryListByLink( partCategoriesUrl + url2);
            LOGGER.debug("PartCategory bez kat url samochodu to: {}", partCategoriesUrl  + url2);

        }
        sessionData.setPartCategory(category[0]);

        PromotedBrandsLoader promotedBrandsLoader = new PromotedBrandsLoader();
        if (part.size() > 0) {
            part = Optional.ofNullable(promotedBrandsLoader.rewritedPartListSorter(part)).orElse(new ArrayList<>());
        }


        req.setAttribute("categoryname", sessionData.getPartCategory());
        req.setAttribute("partCategories", partCategories);
        req.setAttribute("parts", part);
        if(sessionData.isAdmin()==true) {
            req.setAttribute("isadmin", sessionData.isAdmin());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/partCategoryAndSpecificPart.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (sessionData.isLogged() == false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("/googlelogin");
        }
        try {
            Model selectedModel = sessionData.getCar().getModel();
        } catch (NullPointerException e) {
            resp.sendRedirect("/choisingbrand");
        }

        req.setCharacterEncoding("UTF-8");
        Car car = new Car();

        Model selectedModel = sessionData.getCar().getModel();
        String url = "http://infoshareacademycom.2find.ru" + selectedModel.getLink() + "?lang=polish";
        List<Type> carTypeList = carsDataLoader2.getTypesListByLink(url);
        List<Type> selectedCarType = carTypeList.stream().filter(b -> b.getId().equals(req.getParameter("type"))).collect(Collectors.toList());

        car.setCarType(selectedCarType.get(0));
        car.setBrand(sessionData.getCar().getBrand());
        car.setModel(sessionData.getCar().getModel());
        sessionData.setCar(car);

        resp.sendRedirect("/index");
    }
}

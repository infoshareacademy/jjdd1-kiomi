package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
 * Created by arek50 on 2017-04-27.
 */
@WebServlet(urlPatterns = "/searchbyaztec")
public class SearchCarTypeByAztecCode extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarsDataLoader.class);
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    CarIdentityFromAztec carIdentityFromAztec;
    @Inject
    SessionData sessionData;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/caridentitymethod");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        if (sessionData.isLogged() == false) {
            req.setAttribute("errorMessage", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("/googlelogin");
        }

//        GetJsonFromFile getJsonFromFile = new GetJsonFromFile();
        GetJsonFromAtenaWeb getJsonFromAtenaWeb = new GetJsonFromAtenaWeb();
        String aztec = (req.getParameter("aztec") != null) ? req.getParameter("aztec") : "";
LOGGER.info(aztec);
//        CarFromAztecJson aztecCodeFromFile = getJsonFromFile.getJsonFile(aztec);
        CarFromAztecJson aztecCodeFromFile = getJsonFromAtenaWeb.getJsonFile(aztec);
        Car carFromAztec = new Car();
        carFromAztec.setBrand(null);
        carFromAztec.setModel(null);
        carFromAztec.setCarType(null);
        req.setAttribute("action", "searchbyaztec");

        if (aztecCodeFromFile != null) {
            carFromAztec = carIdentityFromAztec.FindingCarByAztecCode(aztecCodeFromFile);
        }

        if (req.getParameter("model") != null) {
            String url = "http://infoshareacademycom.2find.ru" + sessionData.getCar().getBrand().getLink() + "?lang=polish";
            List<Model> modelsList = carsDataLoader2.getModelsListBylink(url);
            List<Model> selectedModel = modelsList.stream().filter(b -> b.getId().equals(req.getParameter("model"))).collect(Collectors.toList());
            carFromAztec.setModel(selectedModel.get(0));
            carFromAztec.setBrand(sessionData.getCar().getBrand());
            sessionData.setCar(carFromAztec);
        } else if (req.getParameter("brand") != null) {
            List<Brand> brandsList = brandsCache.getBrandsList();
            List<Brand> selectedBrand = brandsList.stream().filter(b -> b.getId().equals(req.getParameter("brand"))).collect(Collectors.toList());
            carFromAztec.setBrand(selectedBrand.get(0));
            sessionData.setCar(carFromAztec);
        }

        if (carFromAztec.getBrand() == null) {
            String errorMessage = ("Nie znaleziono marki samochodu. Wybierz z listy.");

            req.setAttribute("brandList", brandsCache.getBrandsList());
            req.setAttribute("errorMessage", errorMessage);
            LOGGER.info("Brand ma rozmiar: ", brandsCache.getBrandsList().size());
            RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingBrand.jsp");
            dispatcher.forward(req, resp);
        } else if (carFromAztec.getModel() == null) {

            List<Model> modelFromList = carIdentityFromAztec.findModel(carFromAztec.getBrand().getLink(), aztecCodeFromFile.getModel(), aztecCodeFromFile.getProductionYear());

            String brandName = carFromAztec.getBrand().getName();
            String brandId = carFromAztec.getBrand().getId();
            String brandLink = carFromAztec.getBrand().getLink();

            req.setAttribute("brand", brandName);
            req.setAttribute("brandId", brandId);

            String errorMessage = null;
            if (modelFromList.size() > 1) {
                errorMessage = ("Znaleziono więcej modeli pasujących do wyszukiwania. Wybierz z listy.");
            } else {
                errorMessage = ("Nie znaleziono modelu samochodu. Wybierz z listy.");
                String url = "http://infoshareacademycom.2find.ru" + brandLink + "?lang=polish";
                //logger:

                modelFromList = carsDataLoader2.getModelsListBylink(url);
            }

            req.setAttribute("modelList", modelFromList);
            req.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingModel.jsp");
            dispatcher.forward(req, resp);
        } else {
            String modelLink = carFromAztec.getModel().getLink();
            String url = "http://infoshareacademycom.2find.ru" + modelLink + "?lang=polish";
            req.setAttribute("action", "index");

            List<Type> carTypeList = carIdentityFromAztec.findCarType(url, aztecCodeFromFile);
            if (carTypeList.size() > 1) {
                String errorMessage = "Znaleziono kilka typów silnika dla tego modelu. Wybierz jeden.";
                req.setAttribute("model", carFromAztec.getModel());
                req.setAttribute("brand", carFromAztec.getBrand());
                req.setAttribute("brandId", carFromAztec.getBrand().getId());

                req.setAttribute("typeList", carTypeList);
                req.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingCarType.jsp");
                dispatcher.forward(req, resp);
            } else if (carTypeList.size() == 1) {
                req.setAttribute("model", carFromAztec.getModel());
                req.setAttribute("brand", carFromAztec.getBrand());
                req.setAttribute("typeList", carTypeList);
                req.setAttribute("brandId", carFromAztec.getBrand().getId());

                carFromAztec.setCarType(carTypeList.get(0));
                RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingCarType.jsp");
                dispatcher.forward(req, resp);
            } else {
                String errorMessage = "Nie znaleziono typu samochodu. Wybierz z listy.";
                req.setAttribute("model", carFromAztec.getModel());
                req.setAttribute("brand", carFromAztec.getBrand());
                List<Type> carTypes = carsDataLoader2.getTypesListByLink(url);
                req.setAttribute("brandId", carFromAztec.getBrand().getId());

                req.setAttribute("typeList", carTypes);
                req.setAttribute("errorMessage", errorMessage);

                RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingCarType.jsp");
                dispatcher.forward(req, resp);

            }

        }
    }
}
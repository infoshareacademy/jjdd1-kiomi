package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
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

/**
 * Created by arek50 on 2017-04-27.
 */
@WebServlet(urlPatterns = "/searchbyaztec")
public class SearchCarTypeByAztecCode extends HttpServlet {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarsDataLoader.class);
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    CarIdentityFromAztec carIdentityFromAztec;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        GetJsonFromFile getJsonFromFile = new GetJsonFromFile();
        CarFromAztecJson aztecCodeFromFile = getJsonFromFile.getJsonFile();//plik w url lub postem
        Car carFromAztec = new Car();
        carFromAztec.setBrand(null);
        carFromAztec.setModel(null);
        carFromAztec.setCarType(null);

        System.out.println(aztecCodeFromFile + "--" + aztecCodeFromFile.getBrand());
        if (aztecCodeFromFile != null) {//obiekt z json
            carFromAztec = carIdentityFromAztec.FindingCarByAztecCode(aztecCodeFromFile);
        }

        if (carFromAztec.getBrand() == null) {
            String errorMessage = ("Nie znaleziono marki samochodu.");

            req.setAttribute("brandList", brandsCache.getBrandsList());
            req.setAttribute("errorMessage", errorMessage);
            LOGGER.info("Brand ma rozmiar: ", brandsCache.getBrandsList().size());
            RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingBrand.jsp");
            dispatcher.forward(req, resp);
        } else if (carFromAztec.getModel() == null) {
            String errorMessage = ("Nie znaleziono modelu samochodu.");

            String brandName = carFromAztec.getBrand().getName();
            String brandLink = carFromAztec.getBrand().getLink();

            req.setAttribute("brand", brandName);


            String url = "http://infoshareacademycom.2find.ru" + brandLink + "?lang=polish";
            //logger:

            List<Model> modelFromList = carsDataLoader2.getModelsListBylink(url);
            req.setAttribute("modelList", modelFromList);
            req.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingModel.jsp");
            dispatcher.forward(req, resp);
        } else {
            String modelLink = carFromAztec.getModel().getLink();
            String url = "http://infoshareacademycom.2find.ru" + modelLink + "?lang=polish";

            List<Type> carTypeList = carIdentityFromAztec.findCarType(url, aztecCodeFromFile);
            if (carTypeList.size() >1) {
                req.setAttribute("model", carFromAztec.getModel());
                req.setAttribute("brand", carFromAztec.getBrand());

                req.setAttribute("typeList", carTypeList);
                RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingCarType.jsp");
                dispatcher.forward(req, resp);
            }
            else if (carTypeList.size() ==1) {
                req.setAttribute("model", carFromAztec.getModel());
                req.setAttribute("brand", carFromAztec.getBrand());
                req.setAttribute("typeList", carTypeList);

                carFromAztec.setCarType(carTypeList.get(0));
                RequestDispatcher dispatcher = req.getRequestDispatcher("formToAcceptAstec.jsp");
                dispatcher.forward(req, resp);
            }
            else {
                String errorMessage = "Nie znaleziono typu samochodu.";
                req.setAttribute("model", carFromAztec.getModel());
                req.setAttribute("brand", carFromAztec.getBrand());
                List<Type> test=carsDataLoader2.getTypesListByLink(url);

                req.setAttribute("typeList", test);
                req.setAttribute("errorMessage", errorMessage);

                RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingCarType.jsp");
                dispatcher.forward(req, resp);

            }

        }
    }
}





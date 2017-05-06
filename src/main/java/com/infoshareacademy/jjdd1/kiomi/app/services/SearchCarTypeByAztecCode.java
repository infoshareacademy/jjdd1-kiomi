package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.BrandsCache;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Car;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.CarFromAztecJson;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arek50 on 2017-04-27.
 */
@WebServlet(urlPatterns = "/searchbyaztecs")
public class SearchCarTypeByAztecCode extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(CarsDataLoader.class);
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    CarIdentityFromAztec carIdentityFromAztec;
//    GetJsonFromFile getJsonFromFile;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        GetJsonFromFile getJsonFromFile = new GetJsonFromFile();
//        CarIdentityFromAztec carIdentityFromAztec = new CarIdentityFromAztec();
        CarFromAztecJson aztecCodeFromFile = getJsonFromFile.getJsonFile();//plik w url lub postem
        Car carFromAztec = new Car();
        carFromAztec.setBrand(null);
        carFromAztec.setModel(null);
        carFromAztec.setCarType(null);

        System.out.println(aztecCodeFromFile + "--" + aztecCodeFromFile.getBrand());
        if (aztecCodeFromFile != null) {//obiekt z json

            carFromAztec = carIdentityFromAztec.FindingCarByAztecCode(aztecCodeFromFile);
            System.out.println("-----" + carFromAztec);
        }

//        if (carFromAztec.getBrand() == null) {
//            String errorMessage = ("Nie znaleziono marki samochodu.");
//
//            req.setAttribute("brands", brandsCache.getBrandsList());
//            req.setAttribute("errorMessage", errorMessage);
//            LOGGER.info("Brand ma rozmiar: {}", brandsCache.getBrandsList().size());
//            RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingBrand.jsp");
//            dispatcher.forward(req, resp);
//        }
        /*else if (carFromAztec.getCarsModel() == null) {
//            String errorMessage = ("Nie znaleziono modelu samochodu.");
//
//            String brandName = carFromAztec.getBrand().getName();
//            String brandLink = carFromAztec.getBrand().getLink();
//
//            req.setAttribute("brandName", brandName);
//
//            Car.setBrand(brandName);
//
//            url = "http://infoshareacademycom.2find.ru" + brandLink + "?lang=polish";
//             CarsDataLoader2 jsonParser = new CarsDataLoader2();
        //logger:
        String url = "http://infoshareacademycom.2find.ru" + link + "?lang=polish";
        Model modelFromList = jsonParser.getModelsListBylink(url);
//            req.setAttribute("models", modelFromList);
//            req.setAttribute("errorMessage", errorMessage);
//
//            RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingModel.jsp");
//            dispatcher.forward(req, resp);
//        }
//     }*/
    }

}



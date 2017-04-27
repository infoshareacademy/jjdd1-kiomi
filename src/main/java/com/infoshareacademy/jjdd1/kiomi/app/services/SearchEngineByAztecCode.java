package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.BrandsCache;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Car;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.CarFromAztecJson;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.CarIdentityFromAztec;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
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
public class SearchEngineByAztecCode extends HttpServlet {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarsDataLoader.class);
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    GetJsonFromFile getJsonFromFile;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.error("dfdfdfdfdf");
        System.out.println("433333333333");
        req.setCharacterEncoding("UTF-8");

//        GetJsonFromFile getJsonFromFile=new GetJsonFromFile();
        CarIdentityFromAztec carIdentityFromAztec=new CarIdentityFromAztec();
        CarFromAztecJson aztecCodeFromFile=getJsonFromFile.getJsonFile();//plik w url lub postem
        CarFromAztecJson aztecCode = getCarFromAztec(aztecCodeFromFile);//do wywalenia razem z funkcją
        Car carFromAztec = new Car();
        carFromAztec.setBrand(null);
        carFromAztec.setModel(null);
        carFromAztec.setCarType(null);

        System.out.println(aztecCodeFromFile+"--"+aztecCodeFromFile.getBrand());
        if (aztecCodeFromFile != null) {//obiekt z json
            carFromAztec = carIdentityFromAztec.FindingCarByAztecCode(aztecCode);
            System.out.println("-----"+carFromAztec);
        }

        if (carFromAztec.getBrand() == null) {
            String errorMessage = ("Nie znaleziono marki samochodu.");

            req.setAttribute("brands", brandsCache.getBrandsList());
            req.setAttribute("errorMessage", errorMessage);
            LOGGER.info("Brand ma rozmiar: {}", brandsCache.getBrandsList().size());
//            RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingBrand.jsp");
//            dispatcher.forward(req, resp);
        }
        /*else if (carFromAztec.getCarsModel() == null) {
//            String errorMessage = ("Nie znaleziono modelu samochodu.");
//
//            String brandName = carFromAztec.getCarsBrand().getName();
//            String brandLink = carFromAztec.getCarsBrand().getLink();
//
//            req.setAttribute("brandName", brandName);
//
//            Car.setCarBrand(brandName);
//
//            url = "http://infoshareacademycom.2find.ru" + brandLink + "?lang=polish";
//
//            DataCarsModels dataCarsModels = parser.parseModelFile(url);
//            req.setAttribute("models", dataCarsModels.getData());
//            req.setAttribute("errorMessage", errorMessage());
//
//            RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingModel.jsp");
//            dispatcher.forward(req, resp);
//        } else {
//            String modelLink = carFromAztec.getCarsModel().getLink();
//            url = "http://infoshareacademycom.2find.ru" + modelLink + "?lang=polish";
//
//            List<CarsEngineAndFuel> engineList = carIdentification.findMatchingEngines(url, aztecData);
//
//            LOGGER.debug("engineList.size = " + engineList.size());
//
//            if (engineList.size() > 1) {
//                String errorMessage = "Znaleziono więcej niż typ silnika.";
//                req.setAttribute("modelName", carFromAztec.getCarsModel());
//                req.setAttribute("brandName", carFromAztec.getCarsBrand());
//
//                formData.setCarBrand(carFromAztec.getCarsBrand().getName());
//                formData.setCarModel(carFromAztec.getCarsModel().getName());
//
//                req.setAttribute("engines", engineList);
//                req.setAttribute("errorMessage", errorMessage);
//
//                RequestDispatcher dispatcher = req.getRequestDispatcher("formToChoisingCarType.jsp");
//                dispatcher.forward(req, resp);
//
//            } else if (engineList.size() == 1) {
//
//                String engineName = engineList.get(0).getName();
//                String engineLink = engineList.get(0).getLink();
//
//                req.setAttribute("engineName", engineList.get(0).getEngine());
//                req.setAttribute("modelName", carFromApi.getCarsModel());
//                req.setAttribute("brandName", carFromApi.getCarsBrand());
//
//                req.setAttribute("isFirstCat", true);
//
//                String engineOut = engineName + ";" + engineLink;
//
//                formData.setCarBrand(carFromApi.getCarsBrand().getName());
//                formData.setCarModel(carFromApi.getCarsModel().getName());
//                formData.setCarEngine(engineName);
//                formData.setEngineLookupString(engineOut);
//
//                url = "http://infoshareacademycom.2find.ru" + engineLink + "?lang=polish";
//
//                JsonDataAutopartCategories autopartCategories = parser.parseCategoryFile(url);
//                req.setAttribute("categories", autopartCategories.getData());
//                req.setAttribute("errorMessage", sessionData.getErrorMessage());
//                req.setAttribute("warningMessage", sessionData.getWarningMessage());
//
//                RequestDispatcher dispatcher = req.getRequestDispatcher("PartFirstCategoryChoosingForm.jsp");
//                dispatcher.forward(req, resp);
//            } else {
//                sessionData.setWarningMessage("Nie znaleziono pasującego modelu silnika. Wybierz ręcznie.");
//                req.setAttribute("modelName", carFromApi.getCarsModel().getName());
//                req.setAttribute("brandName", carFromApi.getCarsBrand().getName());
//
//                formData.setCarBrand(carFromApi.getCarsBrand().getName());
//                formData.setCarModel(carFromApi.getCarsModel().getName());
//
//                DataCarsEngineAndFuel dataCarsEngineAndFuelModels = parser.parseEngineFile(url);
//                req.setAttribute("engines", dataCarsEngineAndFuelModels.getData());
//                req.setAttribute("errorMessage", sessionData.getErrorMessage());
//                req.setAttribute("warningMessage", sessionData.getWarningMessage());
//
//                RequestDispatcher dispatcher = req.getRequestDispatcher("CarEngineChoosingForm.jsp");
//                dispatcher.forward(req, resp);
//            }
//        }*/
    }
    private CarFromAztecJson getCarFromAztec(CarFromAztecJson aztecJson) {


        return aztecJson;
    }
}



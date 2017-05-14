package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.BrandsCache;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Car;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Part;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.PartCategory;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Type;
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

@WebServlet(urlPatterns = "/choisingpartcategory")
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

        if(sessionData.isLogged()==false) {
            req.setAttribute("error", "Nie ma takiego użytkownika. Dostęp zabroniony.");
            resp.sendRedirect("http://localhost:8080/googlelogin");
        }

        try {
            Type selectedCarType = sessionData.getCar().getCarType();
        } catch (NullPointerException e) {
            resp.sendRedirect("http://localhost:8080/choisingbrand");
        }

        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carTypeName = sessionData.getCar().getCarType().getName();

        req.setAttribute("brand", brandName);
        req.setAttribute("model" , modelName);
        req.setAttribute("carType", carTypeName);
        req.setAttribute("sessionUserName", sessionData.getFirstname());

        Object catIdFromJSP = req.getAttribute("catIdFromJSP");
        req.setAttribute("catIdFromJSP", catIdFromJSP);

        String url = "http://infoshareacademycom.2find.ru/api/v2/find/";
        String url2="?lang=polish";
        String brandId = sessionData.getCar().getBrand().getId()+"/";
        String modelId = sessionData.getCar().getModel().getId()+"/";
        String carTypeId = sessionData.getCar().getCarType().getId();

        Map<String, String[]> parameters = req.getParameterMap();

        List<PartCategory> partCategories = new ArrayList<>();
        List<Part> parts = new ArrayList<>();

        LOGGER.debug("Id brandu samochodu to: {}", brandId);
        LOGGER.debug("Id modely samochodu to: {}", modelId);
        LOGGER.debug("Id typu samochodu to: {}", carTypeId);
        LOGGER.debug("Aktualna ścieżka dostępu do zbioru kategorii to: {}", url+brandId+modelId+carTypeId+url2);

//        String catId = "";

//        if (catId.equals("")) {
//            if (!carTypeId.equals("")) {
                partCategories = carsDataLoader2.getPartCategoryListByLink(url+brandId+modelId+carTypeId+url2);
                LOGGER.debug("Kategorie części dla danego typu samochodu: {}", partCategories);
//            }
//        } else {
//            url += "/" + partCategories.get(partCategories.size() - 1);
//
        if(!(catIdFromJSP == null))
            parts = carsDataLoader2.getPartListByLink(url+brandId+modelId+carTypeId+catIdFromJSP+"/"+url2);
//            String[] s = Optional.ofNullable(parameters.get("stock")).orElse(new String[]{""});
//            if (s[0].equals("")) {
//                partCategories = carsDataLoader2.getPartCategoryListByLink(url+url2);
//            }
//        }

        req.setAttribute("partCategories", partCategories);
        req.setAttribute("parts" , parts);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/partCategoryAndSpecificPart.jsp");
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

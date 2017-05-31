package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.BrandsCache;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader2;
import com.infoshareacademy.jjdd1.kiomi.app.services.SessionData;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.StatisticsUtility;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by arek50 on 2017-05-28.
 */
@WebServlet(urlPatterns = "/partstatistics")
public class PartStatistics extends HttpServlet {
    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;
    @Inject
    SessionData sessionData;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (sessionData.isLogged() == false) {
            resp.sendRedirect("/googlelogin");
        } else if(sessionData.isAdmin() == false) {
            resp.sendRedirect("/index");
        }

        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carType = sessionData.getCar().getCarType().getName();

        StatisticsUtility statisticsUtility = new StatisticsUtility();


//        List<Statistics> listOfAllStatistics = statisticsUtility.getAllData();

//        Map<String, BigInteger> brandAndCountMap = statisticsUtility.getBrandAndCountMap();
//
//        Map<String, BigInteger> modelAndCountMap=statisticsUtility.getModelAndCountMap();
//
//        Map<String, BigInteger> typeAndCountMap=statisticsUtility.getTypeAndCountMap();

        Map<String, BigInteger> partBrandAndCountMap=statisticsUtility.getPartBrandAndCountMap();

        Map<String, BigInteger> partCategoryAndCountMap=statisticsUtility.getPartCategoryAndCountMap();

        Map<String, BigInteger> partNameAndCountMap=statisticsUtility.getPartNameAndCountMap();

        req.setAttribute("isAdmin", sessionData.isAdmin());
        req.setAttribute("brand", brandName);
        req.setAttribute("model", modelName);
        req.setAttribute("carType", carType);
        req.setAttribute("sessionUserName", sessionData.getFirstname());
//        req.setAttribute("listOfAllStatistics", listOfAllStatistics);
//        req.setAttribute("brandAndCountMap", brandAndCountMap);
//        req.setAttribute("modelAndCountMap", modelAndCountMap);
//        req.setAttribute("typeAndCountMap", typeAndCountMap);
        req.setAttribute("partBrandAndCountMap", partBrandAndCountMap);
        req.setAttribute("partCategoryAndCountMap", partCategoryAndCountMap);
        req.setAttribute("partNameAndCountMap", partNameAndCountMap);

        RequestDispatcher dispatcher = req.getRequestDispatcher("partStatistics.jsp");
        dispatcher.forward(req, resp);

    }
}

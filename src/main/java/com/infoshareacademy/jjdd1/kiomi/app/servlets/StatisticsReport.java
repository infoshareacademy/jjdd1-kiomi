package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.statistics.Statistics;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.StatisticsUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@WebServlet(urlPatterns = "/statisticsReport")
public class StatisticsReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StatisticsUtility statisticsUtility = new StatisticsUtility();

        List<Statistics> listOfAllStatistics = new ArrayList<>();
        listOfAllStatistics = statisticsUtility.getAllData();

        Map<String, BigInteger> brandAndCountMap = new LinkedHashMap<>();
        brandAndCountMap = statisticsUtility.getBrandAndCountMap();

        Map<String,BigInteger> modelAndCountMap = new LinkedHashMap<>();
        modelAndCountMap=statisticsUtility.getModelAndCountMap();

        Map<String,BigInteger> typeAndCountMap = new LinkedHashMap<>();
        typeAndCountMap=statisticsUtility.getTypeAndCountMap();

        request.setAttribute("listOfAllStatistics", listOfAllStatistics);
        request.setAttribute("brandAndCountMap", brandAndCountMap);
        request.setAttribute("modelAndCountMap", modelAndCountMap);
        request.setAttribute("typeAndCountMap", typeAndCountMap);

//        for (Map.Entry<String, BigInteger> entry : typeAndCountMap.entrySet()) {
//            String key = entry.getKey();
//            BigInteger value = entry.getValue();
//            System.out.println("--> " + key + " " + value);
//        }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/statisticsReport.jsp");
            dispatcher.forward(request, response);
    }
}
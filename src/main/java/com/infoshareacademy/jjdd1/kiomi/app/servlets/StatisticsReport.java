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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/statisticsReport")
public class StatisticsReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StatisticsUtility statisticsUtility=new StatisticsUtility();
        List<Statistics> listOfAllStatistics=new ArrayList<>();

        listOfAllStatistics=statisticsUtility.getAllData();

//        for(Statistics value :listOfAllStatistics){
//            System.out.println(value.getId()+" | "+ value.getCarBrand()+" | " + value.getCarModel() +" | "+ value.getCarType()+" | "+value.getPartCategory()+" | "+value.getCarBrand()+" | "+value.getPartName()+" | "+value.getPartSerial());
//        }

//        List<Statistics> list =new ArrayList<>();
//        list=statisticsUtility.getCarBrandList();
//
//        for(Statistics value :list){
//            System.out.println(value.getId()+" | "+ value.getCarBrand()+" | " + value.getCarModel() +" | "+ value.getCarType()+" | "+value.getPartCategory()+" | "+value.getCarBrand()+" | "+value.getPartName()+" | "+value.getPartSerial());
//        }
//
        request.setAttribute("listOfAllStatistics",listOfAllStatistics);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/statisticsReport.jsp");
        dispatcher.forward(request, response);
    }
}

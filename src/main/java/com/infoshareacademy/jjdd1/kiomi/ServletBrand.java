package com.infoshareacademy.jjdd1.kiomi;

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
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoaderUrl;

/**
 * Created by a on 2017-04-16.
 */

@WebServlet("/servletBrand")
public class ServletBrand extends HttpServlet {

    Brand userBrand = new Brand();
    String userBrandName;
    Model model;

    CarsDataLoaderUrl carsDataLoaderUrl;


    List<Brand> listOfAllBrandObjects=new ArrayList<>();
    List<Model> listOfModelObjectsChosenByUserByBrand=new ArrayList<>();

    public ServletBrand() throws IOException {
        carsDataLoaderUrl=new CarsDataLoaderUrl();
        listOfAllBrandObjects=carsDataLoaderUrl.getListOfAllBrandObjectsFromUrl();
    }

//    @Inject
//  Greeter greeter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userBrandName=request.getParameter("userBrandName").toLowerCase();
        userBrand=carsDataLoaderUrl.getUserBrand(userBrandName);

        listOfModelObjectsChosenByUserByBrand=carsDataLoaderUrl.getListOfModelObjectsChosenByUserByBrand(userBrand);

        request.setAttribute("listOfBrands",listOfAllBrandObjects);
        request.setAttribute("listOfModels",listOfModelObjectsChosenByUserByBrand);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/model.jsp");
        System.out.println("----------------EXIT----------------");

        dispatcher.forward(request,response);
    }
}
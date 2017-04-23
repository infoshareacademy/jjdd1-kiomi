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
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Type;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoaderUrl;

/**
 * Created by a on 2017-04-16.
 */

@WebServlet("/servletModel")
public class ServletModel extends HttpServlet {

    Brand userBrand = new Brand();
    String userBrandName;
    String userModelId;
    Model userModel=new Model();

    CarsDataLoaderUrl carsDataLoaderUrl;

    List<Brand> listOfAllBrandObjects=new ArrayList<>();
    List<Model> listOfModelObjectsChosenByUserByBrand=new ArrayList<>();
    List<Type> listOfTypeObjectsChosenByUserByModel = new ArrayList<>();

    public ServletModel() throws IOException {
        carsDataLoaderUrl=new CarsDataLoaderUrl();
        listOfAllBrandObjects=carsDataLoaderUrl.getListOfAllBrandObjectsFromUrl();
    }

//    @Inject
//  Greeter greeter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userModelId=request.getParameter("userModelId").toLowerCase();
        userBrandName=request.getParameter("userBrandName");
        userBrand=carsDataLoaderUrl.getUserBrand(userBrandName);

        listOfModelObjectsChosenByUserByBrand=carsDataLoaderUrl.getListOfModelObjectsChosenByUserByBrand(userBrand);

        userModel=carsDataLoaderUrl.getUserModel(userModelId);

        listOfTypeObjectsChosenByUserByModel=carsDataLoaderUrl.getlistOfTypeObjectsChosenByUserByModel(userModel);

        request.setAttribute("listOfBrands",listOfAllBrandObjects);
        request.setAttribute("listOfModels",listOfModelObjectsChosenByUserByBrand);
        request.setAttribute("listOfTypes",listOfTypeObjectsChosenByUserByModel);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/type.jsp");
        System.out.println("----------------EXIT----------------");

        dispatcher.forward(request,response);
    }
}

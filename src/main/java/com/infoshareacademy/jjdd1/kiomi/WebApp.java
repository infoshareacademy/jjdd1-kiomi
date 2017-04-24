package com.infoshareacademy.jjdd1.kiomi;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;
import com.infoshareacademy.jjdd1.kiomi.app.services.PromotedBrandsLoader;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by arek50 on 2017-04-15.
 */
@WebServlet(urlPatterns = "/index")
public class WebApp extends HttpServlet {

    @Inject
    CarsDataLoader carsDataLoader;
    @Inject
    BrandsCache brandsCache;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        CarsDataLoader carsDataLoader = new CarsDataLoader();

        Map<String, String[]> parameters = req.getParameterMap();

        try {

            List<Brand> brands = Optional.ofNullable(brandsCache.getBrandsList()).orElse(new ArrayList<>());
//            System.out.println(brands.size());
            req.setAttribute("brandList", brands);


            String[] b = Optional.ofNullable(parameters.get("brand")).orElse(new String[]{""});
            String[] m = Optional.ofNullable(parameters.get("model")).orElse(new String[]{""});
            String[] c = Optional.ofNullable(parameters.get("cat")).orElse(new String[]{""});
            String[] t = Optional.ofNullable(parameters.get("type")).orElse(new String[]{""});


            List<Model> models = carsDataLoader.getModelsListById(b[0]);
            List<Type> carType = carsDataLoader.getTypesListById(m[0]);


            List<Part> categoryForParts=new ArrayList<>();
            List<PartCategory> partCategories = new ArrayList<>();
            for (int i=0;i<=c.length-1;i++) {
                if(i==0) {
                    partCategories = carsDataLoader.getPartCategoryListByIdFromCarType(t[0]);
                } else {
                    partCategories = carsDataLoader.getPartCategoryListByIdFromPartCategory(c[i-1]);
                    System.out.println(i+"::"+c[i]+"::"+partCategories.toString()+"55555");

                }
            }

//            List<PartCategory> partCategories = (c[0].equals("")) ? carsDataLoader.getPartCategoryListByIdFromCarType(t[0]) : carsDataLoader.getPartCategoryListByIdFromPartCategory(c[c.length - 1]);

//            List<Part> part = carsDataLoader.getPartListById(c[c.length - 1]);
//            System.out.println("XXXXXXXXXXXXXXX"+partCategories.get(0).getLink());
//            List<Part> part = carsDataLoader.getPartListById(c[c.length - 1]);
//            System.out.println("partCategories = " + partCategories.get(0).getLink());

            List<Part> part = carsDataLoader.getPartListById(c[c.length - 1]);

            String url = req.getRequestURL().toString() + "?" + req.getQueryString();


            if (part.size() > 0) {
                part = Optional.ofNullable(PromotedBrandsLoader.rewritedPartListSorter(part)).orElse(new ArrayList<>());
            }

            req.setAttribute("modelList", models);
            req.setAttribute("typeList", carType);
            req.setAttribute("menuList", partCategories);
            req.setAttribute("partList", part);
            req.setAttribute("url", url);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        } catch (IOException e) {
            System.out.println("Nie ma pliku na serwerze: " + e.getMessage());
            PrintWriter writer = resp.getWriter();
            writer.append("<b>Nie ma pliku na serwerze<br>" + e.getMessage() + "</b>");
            writer.flush();
        }

    }
}

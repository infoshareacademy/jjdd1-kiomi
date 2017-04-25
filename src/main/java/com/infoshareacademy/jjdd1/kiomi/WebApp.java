package com.infoshareacademy.jjdd1.kiomi;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;
import com.infoshareacademy.jjdd1.kiomi.app.services.PromotedBrandsLoader;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by arek50 on 2017-04-15.
 */
@WebServlet(urlPatterns = "/index")
public class WebApp extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebApp.class);


    @Inject
    CarsDataLoader carsDataLoader;






    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        CarsDataLoader carsDataLoader = new CarsDataLoader();

        Map<String, String[]> parameters = req.getParameterMap();

        try {
            List<Brand> brands = carsDataLoader.getBrandsList();
            req.setAttribute("brandList", brands);
            System.out.println(brands.size());

            String[] b = Optional.ofNullable(parameters.get("brand")).orElse(new String[]{""});
            String[] m = Optional.ofNullable(parameters.get("model")).orElse(new String[]{""});
            String[] c = Optional.ofNullable(parameters.get("cat")).orElse(new String[]{""});
            String[] t = Optional.ofNullable(parameters.get("type")).orElse(new String[]{""});


            List<Model> models = carsDataLoader.getModelsListById(b[0]);
            List<Type> carType = carsDataLoader.getTypesListById(m[0]);
            List<PartCategory> partCategories = (c[0].equals("")) ? carsDataLoader.getPartCategoryListByIdFromCarType(t[0]) : carsDataLoader.getPartCategoryListByIdFromPartCategory(c[c.length - 1]);
            List<Part> part = carsDataLoader.getPartListById(c[c.length - 1]);
            String url = req.getRequestURL().toString() + "?" + req.getQueryString();
            System.out.println(c[0] + "---" + c[c.length - 1]);

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
            LOGGER.error("Brak pliku na serwerze!", e.getMessage());

            PrintWriter writer = resp.getWriter();
            writer.append("<b>Nie ma pliku na serwerze<br>" + e.getMessage() + "</b>");
            writer.flush();
        }

    }
}

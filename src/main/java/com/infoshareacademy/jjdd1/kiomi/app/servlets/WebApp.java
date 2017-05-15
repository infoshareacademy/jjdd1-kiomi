package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader2;
import com.infoshareacademy.jjdd1.kiomi.app.services.PromotedBrandsLoader;
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
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by arek50 on 2017-04-15.
 */
@WebServlet(urlPatterns = "/index2")
public class WebApp extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(WebApp.class);

    @Inject
    CarsDataLoader2 carsDataLoader2;
    @Inject
    BrandsCache brandsCache;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Map<String, String[]> parameters = req.getParameterMap();

        try {

            List<Brand> brands = Optional.ofNullable(brandsCache.getBrandsList()).orElse(new ArrayList<>());
//            System.out.println(brands.size());
            req.setAttribute("brandList", brands);

            String url = "http://infoshareacademycom.2find.ru/api/v2/find/";
            String url2="?lang=polish";

            String[] b = Optional.ofNullable(parameters.get("brand")).orElse(new String[]{""});
            List<Model> models = new ArrayList<>();
            if (!b[0].equals("")) {
                url += "/" + b[0];
                models = carsDataLoader2.getModelsListBylink(url+url2);
            }

            String[] m = Optional.ofNullable(parameters.get("model")).orElse(new String[]{""});
            List<Type> carType = new ArrayList<>();
            if (!m[0].equals("")) {
                url += "/" + m[0];
                carType = carsDataLoader2.getTypesListByLink(url+url2);
            }

            String[] t = Optional.ofNullable(parameters.get("type")).orElse(new String[]{""});
            url += (!m[0].equals("")) ? "/" + t[0] : "";
            String[] c = Optional.ofNullable(parameters.get("cat")).orElse(new String[]{""});

            List<PartCategory> partCategories = new ArrayList<>();
            List<Part> part = new ArrayList<>();
            if (c[0].equals("")) {
                if (!t[0].equals("")) {
                    partCategories = carsDataLoader2.getPartCategoryListByLink(url+url2);
                }
            } else {
                url += "/" + c[c.length - 1];

                part = carsDataLoader2.getPartListByLink(url + "/stock"+url2);
                String[] s = Optional.ofNullable(parameters.get("stock")).orElse(new String[]{""});
                if (s[0].equals("")) {
                    partCategories = carsDataLoader2.getPartCategoryListByLink(url+url2);
                }
            }

            String uri = req.getRequestURL().toString() + "?" + req.getQueryString();

            PromotedBrandsLoader promotedBrandsLoader=new PromotedBrandsLoader();
            if (part.size() > 0) {
                part = Optional.ofNullable(promotedBrandsLoader.rewritedPartListSorter(part)).orElse(new ArrayList<>());
            }

            req.setAttribute("modelList", models);
            req.setAttribute("typeList", carType);
            req.setAttribute("menuList", partCategories);
            req.setAttribute("partList", part);
            req.setAttribute("url", uri);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        } catch (IOException e) {
            LOGGER.error("Brak pliku na serwerze: " + e.getMessage());
            PrintWriter writer = resp.getWriter();
            writer.append("<b>Brak pliku na serwerze<br>" + e.getMessage() + "</b>");
            writer.flush();
        }

    }
}

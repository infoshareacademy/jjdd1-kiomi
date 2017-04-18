package com.infoshareacademy.jjdd1.kiomi;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String word = req.getParameter("word");
//
//        if (Objects.isNull(word)) {
//            resp.sendError(400, "Brak parametru `word`");
//        }
//
//        List<Word> wordList = service.translate(word);
//
//        req.setAttribute("word", word);
//        req.setAttribute("wordList", wordList);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
//        dispatcher.forward(req, resp);


        //wyświetlenie strony z wynikami
//        PrintWriter writer = resp.getWriter();
//        writer.append("<b>hay</b>");
//        writer.flush();
//        String word = req.getParameter("word");

//        if (Objects.isNull(word)) {
//            resp.sendError(400, "Brak parametru `word`");
//        }
//        String[] nazwa= new String[]{"2222", "3333", "4444"};
        CarsDataLoader carsDataLoader = new CarsDataLoader();

        Map<String, String[]> parameters = req.getParameterMap();
//        for (Object key: parameters.keySet())
//        {
//            String keyStr = (String)key;
//            String[] value = (String[])parameters.get(keyStr);
//            System.out.println("Key " + (String)key + "   :   " + Arrays.toString(value));
//        }

//        String[] val = parameters.get("type");
//        System.out.println("qqq"+val[0]);

        List<Brand> brands = carsDataLoader.getBrandsList();
        req.setAttribute("brandList", brands);
        String[] b = Optional.ofNullable(parameters.get("brand")).orElse(new String[]{""});
        String[] m = Optional.ofNullable(parameters.get("model")).orElse(new String[]{""});
        String[] c = Optional.ofNullable(parameters.get("cat")).orElse(new String[]{""});
        String[] t = Optional.ofNullable(parameters.get("type")).orElse(new String[]{""});
        List<Model> models = carsDataLoader.getModelsListById(b[0]);
        List<Type> carType = carsDataLoader.getTypesListById(m[0]);
        List<PartCategory> partCategories = carsDataLoader.getPartCategoryListById((c[0].equals(""))?t[0]:c[0]);
        List<Part> part = carsDataLoader.getPartListById(c[c.length - 1]);
        String url = req.getRequestURL().toString() + "?" + req.getQueryString();

        System.out.println(c.toString());
//        String[] b = new String[brands.size()];

//        for (int i = 0; i < brands.size(); i++){
//            b[i] = brands.get(i).getName();
//    }
//        String[] m = new String[models.size()];
//
//        for (int i = 0; i < brands.size(); i++){
//            b[i] = brands.get(i).getName();
//        }

        req.setAttribute("modelList", models);
        req.setAttribute("typeList", carType);
        req.setAttribute("menuList", partCategories);
        req.setAttribute("partList", part);
        req.setAttribute("url", url);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);


    }
}

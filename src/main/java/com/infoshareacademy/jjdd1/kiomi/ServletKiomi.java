package com.infoshareacademy.jjdd1.kiomi;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a on 2017-04-16.
 */
//@WebServlet(urlPatterns = "/index")
@WebServlet("/servlet")
public class ServletKiomi extends HttpServlet {

    Brand brand;

    List<Brand> listOfBrandObjects=new ArrayList<>();

    @Inject
//    Greeter greeter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("------------------");

        getListOfAllBrandObjectsFromUrl();

        request.setAttribute("listOFBrands",listOfBrandObjects);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");









//------- start ------------- na sztywno KIA ------------------


//------- end ------------- na sztywno KIA ------------------

        System.out.println("----------------EXIT----------------");



dispatcher.forward(request,response);



//
//        ServletContext context=getServletContext();
//
//        String max=context.getInitParameter("maxinfo");
//
//        System.out.println("max: "+max);
//
//        // analiza danych wejściowych
//        String name = request.getParameter("name");
//
//        //wywołanie komponentów
////        String response = greeter.sayHello(name);
//
//        //wyświetlenie strony z wynikami
//
//
//
//
//        response.setContentType("text/html");
//
//        PrintWriter writer = response.getWriter();
//        writer.append("SiemNADER");
//
//        writer.println(request.getParameter("brand"));
//
//        writer.append("<b>" + name + "</b>");
//        writer.flush();



    }

    private  List<Brand> getListOfAllBrandObjectsFromUrl() throws IOException {

        JSONObject jsonObject = readJsonFromUrl("http://infoshareacademycom.2find.ru/api/v2?lang=polish");

//        System.out.println("datatype: "+jsonObject.get("datatype"));
//        System.out.println("data: "+jsonObject.get("data"));

        JSONArray arr= (JSONArray) jsonObject.get("data");

        for (int i = 0; i < arr.length(); i++) {
//            System.out.println(arr.getJSONObject(i).getClass()+"= "+arr.getJSONObject(i).get("id"));
            brand=new Brand();
            brand.setId(arr.getJSONObject(i).get("id").toString());
            brand.setName(arr.getJSONObject(i).get("name").toString());
            brand.setName_clear(arr.getJSONObject(i).get("name_clear").toString());
            brand.setHas_image((Boolean) arr.getJSONObject(i).get("has_image"));
            brand.setLink( arr.getJSONObject(i).get("link").toString());
//            brand.setHas_image(arr.getJSONObject(i).get("has_image"));
            listOfBrandObjects.add(brand);

        }

        for(Brand value:listOfBrandObjects ){
            System.out.println("id:  "+value.getId());
            System.out.println("name:  "+value.getName());
            System.out.println("clear name: "+value.getName_clear());
            System.out.println("has image: "+value.getHas_image());
            System.out.println("link: "+value.getLink());
            System.out.println("===========");
        }

        return listOfBrandObjects;
    }


    private  String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public  JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

}
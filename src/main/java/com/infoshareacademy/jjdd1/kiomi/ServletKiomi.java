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
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
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
    String userBrandName;
    Model model;

    List<Brand> listOfAllBrandObjects=new ArrayList<>();
    List<Model> listOfModelObjectsChosenByUserByBrand=new ArrayList<>();

    public ServletKiomi() throws IOException {
        listOfAllBrandObjects=getListOfAllBrandObjectsFromUrl();
    }

    @Inject
//    Greeter greeter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userBrandName=request.getParameter("userBrandName").toLowerCase();

        getListOfModelObjectsChosenByUserByBrand();
        request.setAttribute("listOfBrands",listOfAllBrandObjects);
        request.setAttribute("listOfModels",listOfModelObjectsChosenByUserByBrand);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/model.jsp");

        System.out.println("----------------EXIT----------------");

dispatcher.forward(request,response);

    }

    private List<Model> getListOfModelObjectsChosenByUserByBrand() throws IOException {

        listOfModelObjectsChosenByUserByBrand.clear();

        System.out.println("User Brand name: "+userBrandName);

        for(int i=0;i<listOfAllBrandObjects.size();i++){
            Brand tempBrand=new Brand();

            if(userBrandName.equals(listOfAllBrandObjects.get(i).getName_clear())){

                tempBrand.setId(listOfAllBrandObjects.get(i).getId());
                tempBrand.setLink(listOfAllBrandObjects.get(i).getLink());
                if(tempBrand.getId()!=null && tempBrand.getId().equals(listOfAllBrandObjects.get(i).getId())){
                    System.out.println(tempBrand.getLink());

                    JSONObject jsonObject = readJsonFromUrl("http://infoshareacademycom.2find.ru"+tempBrand.getLink());

                    JSONArray arr= (JSONArray) jsonObject.get("data");

                    for (int j = 0; j < arr.length(); j++) {

                        model=new Model();

                        model.setId(arr.getJSONObject(j).get("id").toString());
                        model.setName(arr.getJSONObject(j).get("name").toString());
                        model.setStart_month(arr.getJSONObject(j).get("start_month").toString());
                        model.setStart_year(arr.getJSONObject(j).get("start_year").toString());
                        model.setEnd_month( arr.getJSONObject(j).get("end_month").toString());
                        model.setEnd_year( arr.getJSONObject(j).get("end_year").toString());
                        model.setVehicle_group( arr.getJSONObject(j).get("vehicle_group").toString());
                        model.setLink( arr.getJSONObject(j).get("link").toString());

                        listOfModelObjectsChosenByUserByBrand.add(model);
                    }
                }

            }
        }
        return listOfModelObjectsChosenByUserByBrand;
    }

    private  List<Brand> getListOfAllBrandObjectsFromUrl() throws IOException {

        JSONObject jsonObject = readJsonFromUrl("http://infoshareacademycom.2find.ru/api/v2?lang=polish");

        JSONArray arr= (JSONArray) jsonObject.get("data");

        for (int i = 0; i < arr.length(); i++) {

            brand=new Brand();

            brand.setId(arr.getJSONObject(i).get("id").toString());
            brand.setName(arr.getJSONObject(i).get("name").toString());
            brand.setName_clear(arr.getJSONObject(i).get("name_clear").toString());
            brand.setHas_image((Boolean) arr.getJSONObject(i).get("has_image"));
            brand.setLink( arr.getJSONObject(i).get("link").toString());

            listOfAllBrandObjects.add(brand);
        }

//        for(Brand value:listOfAllBrandObjects ){
//            System.out.println("id:  "+value.getId());
//            System.out.println("name:  "+value.getName());
//            System.out.println("clear name: "+value.getName_clear());
//            System.out.println("has image: "+value.getHas_image());
//            System.out.println("link: "+value.getLink());
//            System.out.println("===========");
//        }

        return listOfAllBrandObjects;
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
package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Type;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2017-04-21.
 */
public class CarsDataLoaderUrl {

    Brand userBrand = new Brand();
    Model userModel = new Model();
    List<Brand> listOfAllBrandObjects = new ArrayList<>();
    List<Model> listOfModelObjectsChosenByUserByBrand = new ArrayList<>();
    List<Type> listOfTypeObjectsChosenByUserByModel = new ArrayList<>();

    public CarsDataLoaderUrl() throws IOException {
        listOfAllBrandObjects = getListOfAllBrandObjectsFromUrl();
    }

    public List<Brand> getListOfAllBrandObjectsFromUrl() throws IOException {

        JSONObject jsonObject = readJsonFromUrl("http://infoshareacademycom.2find.ru/api/v2?lang=polish");
        JSONArray arr = (JSONArray) jsonObject.get("data");

        for (int i = 0; i < arr.length(); i++) {

            Brand tempBrand = new Brand();

            tempBrand.setId(arr.getJSONObject(i).get("id").toString());
            tempBrand.setName(arr.getJSONObject(i).get("name").toString());
            tempBrand.setName_clear(arr.getJSONObject(i).get("name_clear").toString());
            tempBrand.setHas_image((Boolean) arr.getJSONObject(i).get("has_image"));
            tempBrand.setLink(arr.getJSONObject(i).get("link").toString());

            listOfAllBrandObjects.add(tempBrand);
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

    public Brand getUserBrand(String userBrandName) {

        for (int i = 0; i < listOfAllBrandObjects.size(); i++) {
            if (userBrandName.toLowerCase().equals(listOfAllBrandObjects.get(i).getName_clear())) {
                userBrand.setId(listOfAllBrandObjects.get(i).getId());
                userBrand.setLink(listOfAllBrandObjects.get(i).getLink());
            }
        }
        return userBrand;
    }

    public Model getUserModel(String userModelId) {
        System.out.println("size: "+listOfModelObjectsChosenByUserByBrand.size());
        for (int i = 0; i < listOfModelObjectsChosenByUserByBrand.size(); i++) {
            if (userModelId.toLowerCase().equals(listOfModelObjectsChosenByUserByBrand.get(i).getId())) {
                userModel.setId(listOfModelObjectsChosenByUserByBrand.get(i).getId());
                userModel.setLink(listOfModelObjectsChosenByUserByBrand.get(i).getLink());
            }
        }
        return userModel;
    }

    public List<Model> getListOfModelObjectsChosenByUserByBrand(Brand userBrand) throws IOException {

        listOfModelObjectsChosenByUserByBrand.clear();

        System.out.println("User Brand id: " + userBrand.getId());
        System.out.println(listOfAllBrandObjects.size());
        System.out.println(userBrand.getLink());

        JSONObject jsonObject = readJsonFromUrl("http://infoshareacademycom.2find.ru" + userBrand.getLink());
        JSONArray arr = (JSONArray) jsonObject.get("data");

        for (int i = 0; i < arr.length(); i++) {

            Model tempModel = new Model();

            tempModel.setId(arr.getJSONObject(i).get("id").toString());
            tempModel.setName(arr.getJSONObject(i).get("name").toString());
            tempModel.setStart_month(arr.getJSONObject(i).get("start_month").toString());
            tempModel.setStart_year(arr.getJSONObject(i).get("start_year").toString());
            tempModel.setEnd_month(arr.getJSONObject(i).get("end_month").toString());
            tempModel.setEnd_year(arr.getJSONObject(i).get("end_year").toString());
            tempModel.setVehicle_group(arr.getJSONObject(i).get("vehicle_group").toString());
            tempModel.setLink(arr.getJSONObject(i).get("link").toString());

            listOfModelObjectsChosenByUserByBrand.add(tempModel);
        }

//            for(Model value:listOfModelObjectsChosenByUserByBrand ){
//            System.out.println("id:  "+value.getId());
//            System.out.println("name:  "+value.getName());
//            System.out.println("getStart_month: "+value.getStart_month());
//            System.out.println("getStart_year: "+value.getStart_year());
//            System.out.println("name:  "+value.getEnd_month());
//            System.out.println("getStart_month: "+value.getEnd_year());
//            System.out.println("getStart_year: "+value.getVehicle_group());
//            System.out.println("link: "+value.getLink());
//            System.out.println("===========");
//        }

        return listOfModelObjectsChosenByUserByBrand;
    }

    public List<Type> getlistOfTypeObjectsChosenByUserByModel(Model userModel) throws IOException {

        listOfTypeObjectsChosenByUserByModel.clear();

        System.out.println("User Model id: " + userModel.getId());
        System.out.println(listOfAllBrandObjects.size());
        System.out.println(userModel.getLink());

        JSONObject jsonObject = readJsonFromUrl("http://infoshareacademycom.2find.ru" + userModel.getLink());
        JSONArray arr = (JSONArray) jsonObject.get("data");

        for (int i = 0; i < arr.length(); i++) {

            Type tempType = new Type();

            tempType.setId(arr.getJSONObject(i).get("id").toString());
            tempType.setBrand_id(arr.getJSONObject(i).get("brand_id").toString());
            tempType.setModel_id(arr.getJSONObject(i).get("model_id").toString());
            tempType.setName(arr.getJSONObject(i).get("name").toString());
            tempType.setStart_month(arr.getJSONObject(i).get("start_month").toString());
//          tempType.setStart_year(arr.getJSONObject(i).get("start_year").toString());
            tempType.setEnd_month(arr.getJSONObject(i).get("end_month").toString());
            tempType.setEnd_year(arr.getJSONObject(i).get("end_year").toString());
//            tempType.setCcm((int) arr.getJSONObject(i).get("ccm"));
//            tempType.setKw((int) arr.getJSONObject(i).get("kw"));
//            tempType.setCylinders((int) arr.getJSONObject(i).get("cylinders"));
            tempType.setEngine(arr.getJSONObject(i).get("engine").toString());
            tempType.setEngine_txt(arr.getJSONObject(i).get("engine_txt").toString());
            tempType.setFuel(arr.getJSONObject(i).get("fuel").toString());
            tempType.setBody(arr.getJSONObject(i).get("body").toString());
            tempType.setAxle(arr.getJSONObject(i).get("axle").toString());
            tempType.setMax_weight(arr.getJSONObject(i).get("max_weight").toString());
            tempType.setLink(arr.getJSONObject(i).get("link").toString());

            listOfTypeObjectsChosenByUserByModel.add(tempType);
        }

            for(Type value:listOfTypeObjectsChosenByUserByModel ){
            System.out.println("Type id:  "+value.getId());
            System.out.println("===========");
        }

        return listOfTypeObjectsChosenByUserByModel;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
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

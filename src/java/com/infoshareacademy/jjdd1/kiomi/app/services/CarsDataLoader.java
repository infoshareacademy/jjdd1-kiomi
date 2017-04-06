package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.PartCategory;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Type;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CarsDataLoader {
    private static final String BRANDS_URI = "src/Resources/brands.json";
    private static final String MODELS_URI = "src/Resources/FORD - modele.json";
    private static final String PART_URI = "src/Resources/FORD FOCUS III 1.5 TDCi XXDA - czйШci - hamulce - tarczowe.json";
    private static final String PARTCATEGORY_URI = "src/Resources/FORD FOCUS III 1.5 TDCi XXDA - czйШci.json";
    private static String JSON_DATA_TAG = "data";

    private String URI = "/FORD FOCUS III - typy.json";

    Set<Brand> brand = new HashSet();
    Set<Model> model = new HashSet();
    Set<Type> type = new HashSet();
    Set<PartCategory> partcategory = new HashSet();

    public static void main(String[] args) throws IOException {
        loadData2();
    }



    public static void loadData2() throws IOException {

        Gson gson = new Gson();
        FileReader fileReader = new FileReader(BRANDS_URI);

        JsonObject response = gson.fromJson(fileReader, JsonObject.class);
        JsonElement data = response.get(JSON_DATA_TAG);

        java.lang.reflect.Type type = new TypeToken<Set<Brand>>(){}.getType();
        Set<Brand> mySet = gson.fromJson(data, type);
//Brand br=new Brand("f9");

        for (Brand x:mySet) {
            if(x.getId().equals("f9")) {
                System.out.println(x.getId());
            }

        }


    }
}



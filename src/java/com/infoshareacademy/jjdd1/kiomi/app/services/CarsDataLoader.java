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

/*
Refaktoryzacja DRY
Zapis do zmiennej globalnej, żeby zapisać dane
Czy lepiej zapisać od razu wszystkie pliki do zmiennych, czy przy zapytaniach
hashset bez diamentow
Zmiana typów w kilku miejscach
return dwóch typów informacji

 */
public class CarsDataLoader {
    private static final String BRANDS_URI = "src/Resources/brands.json";
    private static final String MODELS_URI = "src/Resources/FORD - modele.json";
    private static final String PART_URI = "src/Resources/FORD FOCUS III 1.5 TDCi XXDA - czйШci - hamulce - tarczowe.json";
    private static final String PARTCATEGORY_URI = "src/Resources/FORD FOCUS III 1.5 TDCi XXDA - czйШci.json";
    private static final String TYPE_URI = "/FORD FOCUS III - typy.json";
    private static String JSON_DATA_TAG = "data";



    static Set<Brand> brand = new HashSet();
    static Set<Model> model = new HashSet();
    static Set<Type> type = new HashSet();
    static Set<PartCategory> partcategory = new HashSet();

    public static void main(String[] args) throws IOException {
        // System.out.println(loadDataFromClearName("innocenti"));;
    }

public static void loadBrand(String name) throws IOException {
    //FileReader fileReader = new FileReader(BRANDS_URI);
    //loadDataBrandsFile();
    System.out.println(brand.toString());
}

    public static List<Model> loadDataBrandsFile(String name) {
        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(BRANDS_URI);

            JsonObject response = gson.fromJson(fileReader, JsonObject.class);
            JsonElement data = response.get(JSON_DATA_TAG);

            java.lang.reflect.Type type = new TypeToken<Set<Brand>>() {
            }.getType();
            brand = gson.fromJson(data, type);

            for (Brand x : brand) {
                if (x.getName_clear().equals(name)) {

                    return loadDataModelsFile(x.getId());

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Model> loadDataModelsFile(String name) {
        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(MODELS_URI);

            JsonObject response = gson.fromJson(fileReader, JsonObject.class);
            JsonElement data = response.get(JSON_DATA_TAG);

            java.lang.reflect.Type type = new TypeToken<Set<Model>>() {
            }.getType();
             model = gson.fromJson(data, type);

            List<Model> temporaryModel = new ArrayList<Model>();

            for (Model x : model) {
                String[] links = x.getLink().split("/");

                if (links[4].equals(name)) {
                    temporaryModel.add(x);
                }

            }
            return temporaryModel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



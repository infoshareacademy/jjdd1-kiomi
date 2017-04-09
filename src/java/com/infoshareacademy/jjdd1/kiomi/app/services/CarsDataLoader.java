package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
Refaktoryzacja
czy lepiej zapisać pliki od razu, czy przy zapytaniach
hashset bez diamentu
 */
public class CarsDataLoader {
    private static final String BRANDS_URI = "src/Resources/brands.json";
    private static final String MODELS_URI = "src/Resources/FORD - modele.json";
    private static final String PART_URI = "src/Resources/FORD FOCUS III 1.5 TDCi XXDA - czйШci - hamulce - tarczowe.json";
    private static final String PARTCATEGORY_URI = "src/Resources/FORD FOCUS III 1.5 TDCi XXDA - czйШci.json";
    private static final String PARTSUBCATEGORY_URI = "src/Resources/FORD FOCUS III 1.5 TDCi XXDA - czйШci - hamulce.json";
    private static final String TYPES_URI = "src/Resources/FORD FOCUS III - typy.json";

    private static String JSON_DATA_TAG = "data";
    private static String JSON_BREADCRUMBS_TAG = "breadcrumbs";

    static Set<Brand> brand = new HashSet();
    static Set<Model> model = new HashSet();
    static Set<Type> carType = new HashSet();
    static Set<PartCategory> partCategory = new HashSet();
    static Set<Part> part = new HashSet();
    static List<Breadcrumbs> breadcrumbs = new ArrayList();

    static <T> T JSONLoader(T c, String file) {
        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(file);

            JsonObject response = gson.fromJson(fileReader, JsonObject.class);
            JsonElement data = response.get(JSON_DATA_TAG);

            if (c != brand) {
                JsonElement bread = response.get(JSON_BREADCRUMBS_TAG);
                breadcrumbs = gson.fromJson(bread, new TypeToken<List<Breadcrumbs>>() {
                }.getType());
            }

            if (c == brand) {
                return gson.fromJson(data, new TypeToken<Set<Brand>>() {
                }.getType());
            } else if (c == model) {
                return gson.fromJson(data, new TypeToken<Set<Model>>() {
                }.getType());
            } else if (c == carType) {
                return gson.fromJson(data, new TypeToken<Set<Type>>() {
                }.getType());
            } else if (c == partCategory) {
                return gson.fromJson(data, new TypeToken<Set<PartCategory>>() {
                }.getType());
            } else if (c == part) {
                return gson.fromJson(data, new TypeToken<Set<Part>>() {
                }.getType());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Set<Brand> getBrandsList() {
        return JSONLoader(brand, BRANDS_URI);
    }

    public static Set<Model> getModelsList() {
        return JSONLoader(model, MODELS_URI);
    }

    public static Set<Type> getCarTypesList() {
        return JSONLoader(carType, TYPES_URI);
    }

    public static Set<PartCategory> getPartCategoryList() {
        return JSONLoader(partCategory, PARTCATEGORY_URI);
    }

    public static Set<PartCategory> getPartSubCategoryList() {
        return JSONLoader(partCategory, PARTSUBCATEGORY_URI);
    }

    public static Set<Part> getPartList() {
        return JSONLoader(part, PART_URI);
    }

    public static String getDataFromBreadcrumbs() {
        String[] links = breadcrumbs.get(breadcrumbs.size() - 1).getLink().split("/");

        return links[links.length - 1];
    }

    public static Set<Model> getModelsListById(String id) {
        model = getModelsList();

        Set<Model> temporaryModel = new HashSet<Model>();
        for (Model x : model) {
            String[] links = x.getLink().split("/");

            if (links[4].equals(id)) {
                temporaryModel.add(x);
            }

        }
        return temporaryModel;
    }

    public static Set<Type> getTypesListById(String id) {
        carType = getCarTypesList();


        Set<Type> temporaryType = new HashSet<Type>();

        for (Type x : carType) {
            String[] links = x.getLink().split("/");
            if (links[5].equals(id)) {
                temporaryType.add(x);
            }

        }
        return temporaryType;

    }

    public static Set<PartCategory> getPartCategoryListById(String id) {

        partCategory = (breadcrumbs.size() < 3) ? getPartCategoryList() : getPartSubCategoryList();

        Set<PartCategory> temporaryPartCategory = new HashSet<PartCategory>();

        for (PartCategory x : partCategory) {

            if (getDataFromBreadcrumbs().equals(id)) {
                temporaryPartCategory.add(x);
            }

        }
        return temporaryPartCategory;
    }

    public static Set<Part> getPartListById(String id) {
        part = getPartList();


        Set<Part> temporaryPart = new HashSet<Part>();

        for (Part x : part) {
            if (getDataFromBreadcrumbs().equals(id)) {
                temporaryPart.add(x);
            }

        }
        return temporaryPart;

    }


//    public static Set<Part> getPartListById(String id) {
//
//        part = getPartList();
//
//        Set<PartCategory> temporaryPartCategory = new HashSet<PartCategory>();
//
//        for (PartCategory x : partCategory) {
//            String[] links = x.getLink().split("/");
//
//            if (links[6].equals(id)) {
//                temporaryPartCategory.add(x);
//            }
//
//        }
//        return temporaryPartCategory;
//    }




}



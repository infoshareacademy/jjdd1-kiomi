package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@ApplicationScoped
public class CarsDataLoader {
    private static final String BRANDS_URI = "brands.json";
//    private static final String MODELS_URI = "KIA - modele.json";
//    private static final String PART_URI = "KIA RIO 1.4 CVVT G4FA - czesci - filtry - oleju.json";
//    private static final String PARTCATEGORY_URI = "KIA RIO 1.4 CVVT G4FA - czesci.json";
//    private static final String PARTSUBCATEGORY_URI = "KIA RIO 1.4 CVVT G4FA - czesci - filtry.json";
//    private static final String TYPES_URI = "KIA typy.json";
    static final String RESOURCES_DIR = "kiomi";
    private static final String MODELS_URI = "FORD - modele.json";
    private static final String PART_URI = "FORD FOCUS III 1.5 TDCi XXDA - czesci - hamulce - tarczowe.json";
    private static final String PARTCATEGORY_URI = "FORD FOCUS III 1.5 TDCi XXDA - czesci.json";
    private static final String PARTSUBCATEGORY_URI = "FORD FOCUS III 1.5 TDCi XXDA - czesci - hamulce.json";
    private static final String TYPES_URI = "FORD FOCUS III - typy.json";

    private static final int FIRST_ELEMENT = 0;

    private static String JSON_DATA_TAG = "data";
    private static String JSON_BREADCRUMBS_TAG = "breadcrumbs";

    static List<Brand> brand = new ArrayList();
    static List<Model> model = new ArrayList();
    static List<Type> carType = new ArrayList();
    static List<PartCategory> partCategory = new ArrayList();
    static List<Part> part = new ArrayList();
    static List<BreadcrumbsBuilder> breadcrumbs = new ArrayList();
    private static final Logger LOGGER = LoggerFactory.getLogger(CarsDataLoader.class);

    static <T> T jsonLoader(T c, String file) throws IOException {
            Gson gson = new Gson();

            Path root = Paths.get(System.getProperty("java.io.tmpdir")).resolve(RESOURCES_DIR);
            LOGGER.debug("Odczyt plików json z bazy danych.");
            byte[] bytes = Files.readAllBytes(root.resolve(file));
            BufferedReader bufferedReader = Files.newBufferedReader(root.resolve(file));

            JsonObject response = gson.fromJson(bufferedReader, JsonObject.class);
            JsonElement data = response.get(JSON_DATA_TAG);

            if (c != brand) {
                JsonElement bread = response.get(JSON_BREADCRUMBS_TAG);
                breadcrumbs = gson.fromJson(bread, new TypeToken<List<BreadcrumbsBuilder>>() {
                }.getType());
            }
            if (c == brand) {
                return gson.fromJson(data, new TypeToken<List<Brand>>() {
                }.getType());
            } else if (c == model) {
                return gson.fromJson(data, new TypeToken<List<Model>>() {
                }.getType());
            } else if (c == carType) {
                return gson.fromJson(data, new TypeToken<List<Type>>() {
                }.getType());
            } else if (c == partCategory) {
                return gson.fromJson(data, new TypeToken<List<PartCategory>>() {
                }.getType());
            } else if (c == part) {
                return gson.fromJson(data, new TypeToken<List<Part>>() {
                }.getType());
            }

        return c;
    }

    public List<Brand> getBrandsList() throws IOException {
        return jsonLoader(brand, BRANDS_URI);

    }

    public static List<Model> getModelsList() throws IOException {
        return jsonLoader(model, MODELS_URI);
    }

    public static List<Type> getCarTypesList() throws IOException {
        return jsonLoader(carType, TYPES_URI);
    }

    public static List<PartCategory> getPartCategoryList() throws IOException {
        return jsonLoader(partCategory, PARTCATEGORY_URI);
    }

    public static List<PartCategory> getPartSubCategoryList() throws IOException {
        return jsonLoader(partCategory, PARTSUBCATEGORY_URI);
    }

    public static List<Part> getPartList() throws IOException {
        return jsonLoader(part, PART_URI);
    }

    public static String getDataFromBreadcrumbs() {
        String[] links = breadcrumbs.get(breadcrumbs.size() - 1).getLink().split("/");

        return (links[links.length - 1].equals("stock"))?links[links.length - 2]:links[links.length - 1];
    }

    public List<Model> getModelsListById(String id) throws IOException {
        model = getModelsList();
        LOGGER.debug("Liczba wczytanych modeli samochodów: "+model.size());
        LOGGER.debug(String.format("Pierwszy element z listy wczytanych modeli %s", model.get(FIRST_ELEMENT)));
        List<Model> temporaryModel = new ArrayList<>();
        for (Model x : model) {
            String[] links = x.getLink().split("/");

            if (links[4].equals(id)) {
                temporaryModel.add(x);
            }

        }
        LOGGER.debug("Liczba wyników wyszukiwania po id: "+temporaryModel.size());


        if(!temporaryModel.isEmpty()) {
            LOGGER.debug(String.format("Pierwszy element z listy wyszukanych modeli samochodów po id %s", temporaryModel.get(FIRST_ELEMENT)));
        }

        return temporaryModel;
    }

    public List<Type> getTypesListById(String id) throws IOException {
        carType = getCarTypesList();
        LOGGER.debug("Ilość wyników wyszukiwania po typie samochodu: "+carType.size());
        LOGGER.debug(String.format("Pierwszy element z listy wyszukiwania po typie samochodu %s", carType.get(FIRST_ELEMENT)));

        LOGGER.debug("Ilość wyników wyszukiwania po typie samochodu: "+carType.size());
        LOGGER.debug(String.format("Pierwszy element z listy wyszukiwania po typie samochodu %s", carType.get(FIRST_ELEMENT)));

        List<Type> temporaryType = new ArrayList<>();

        for (Type x : carType) {
            String[] links = x.getLink().split("/");
            if (links[5].equals(id)) {
                temporaryType.add(x);
            }

        }
      
        LOGGER.debug("Ilość wyników wyszukiwania po wyborze typu: "+temporaryType.size());
//        LOGGER.debug(String.format("Pierwszy element z listy wyszukanych typów %s", temporaryType.get(FIRST_ELEMENT)));
        return temporaryType;

    }
    public List<PartCategory> getPartCategoryListByIdFromPartCategory(String id) throws IOException {
        partCategory = getPartSubCategoryList();
        return getPartCategoryListById(id);

    }

    public List<PartCategory> getPartCategoryListByIdFromCarType(String id) throws IOException {
        partCategory = getPartCategoryList();
        return getPartCategoryListById(id);
    }
     public List<PartCategory> getPartCategoryListByIdForTerminal(String id) throws IOException {
         partCategory = (breadcrumbs.size() < 3) ? getPartCategoryList() : getPartSubCategoryList();
        return getPartCategoryListById(id);
    }


    public List<PartCategory> getPartCategoryListById(String id) throws IOException {

        List<PartCategory> temporaryPartCategory = new ArrayList<PartCategory>();

        for (PartCategory x : partCategory) {

            if (getDataFromBreadcrumbs().equals(id)) {
                temporaryPartCategory.add(x);
            }

        }
        return temporaryPartCategory;
    }

    public List<Part> getPartListById(String id) throws IOException {


        part = getPartList();


        List<Part> temporaryPart = new ArrayList<Part>();

        for (Part x : part) {
            if (getDataFromBreadcrumbs().equals(id)) {
                temporaryPart.add(x);
            }

        }

        return temporaryPart;

    }


}



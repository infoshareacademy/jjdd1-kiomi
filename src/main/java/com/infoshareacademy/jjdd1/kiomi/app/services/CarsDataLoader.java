package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class CarsDataLoader {
    final String DEFAULT_URL = "http://infoshareacademycom.2find.ru/api/v2?lang=polish";
    static final String RESOURCES_DIR = "kiomi";
    private static final int FIRST_ELEMENT = 0;
    private static String JSON_DATA_TAG = "data";
    private static String JSON_BREADCRUMBS_TAG = "breadcrumbs";
    private static final Logger LOGGER = LogManager.getLogger(CarsDataLoader.class);

    @Inject
    BrandsCache brandsCache;

    static List<Brand> brand = new ArrayList();
    static List<Model> model = new ArrayList();
    static List<Type> carType = new ArrayList();
    static List<PartCategory> partCategory = new ArrayList();
    static List<Part> part = new ArrayList();
    static List<BreadcrumbsBuilder> breadcrumbs = new ArrayList();


    static <T> T jsonLoader(T c, String file) throws IOException {

        Gson gson = new Gson();
        InputStream is = new URL(file).openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

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
        return jsonLoader(brand, DEFAULT_URL);
    }

    public static List<Model> getModelsList(String url) throws IOException {
        return jsonLoader(model, url);
    }

    public static List<Type> getCarTypesList(String url) throws IOException {
        return jsonLoader(carType, url);
    }

    public static List<PartCategory> getPartCategoryList(String url) throws IOException {
        return jsonLoader(partCategory, url);
    }

    public static List<PartCategory> getPartSubCategoryList(String url) throws IOException {
        return jsonLoader(partCategory, url);
    }

    public static List<Part> getPartList(String url) throws IOException {
        return jsonLoader(part, url);
    }

    public List<Model> getModelsListByIdForTerminal(String id) throws IOException {
        brand = getBrandsList();
        List<Brand> element = brand.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (element.isEmpty()) {
            return new ArrayList<>();
        }
        String link = element.get(0).getLink();
        String url = "http://infoshareacademycom.2find.ru" + link + "?lang=polish";
        model = getModelsList(url);
        return model;
    }

    public List<Model> getModelsListById(String id) throws IOException {

        brand = brandsCache.getBrandsList();
        List<Brand> element = brand.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (element.isEmpty()) {
            return new ArrayList<>();

        }
        String link = element.get(0).getLink();
        String url = "http://infoshareacademycom.2find.ru" + link + "?lang=polish";
        model = getModelsList(url);

        LOGGER.debug("Liczba wyników wyszukiwania po id: " + model.size());
        if (!model.isEmpty()) {
            LOGGER.debug(String.format("Pierwszy element z listy wyszukanych modeli samochodów po id %s", model.get(FIRST_ELEMENT)));
        }
        return model;
    }

    public List<Type> getTypesListById(String id) throws IOException {

        List<Model> element = model.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (element.isEmpty()) {
            return new ArrayList<>();
        }
        String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
        carType = getCarTypesList(url);

        LOGGER.debug("Ilość wyników wyszukiwania po typie samochodu: " + carType.size());
        LOGGER.debug(String.format("Pierwszy element z listy wyszukiwania po typie samochodu %s", carType.get(FIRST_ELEMENT)));

        LOGGER.debug("Ilość wyników wyszukiwania po wyborze typu: " + carType.size());
        LOGGER.debug(String.format("Pierwszy element z listy wyszukanych typów %s", carType.get(FIRST_ELEMENT)));
        return carType;


    }

    public List<PartCategory> getPartCategoryListByIdFromPartCategory(String id) throws IOException {

        List<PartCategory> element = getElementFromList(id);
        if (element.isEmpty()) {
            return new ArrayList<>();
        }
        String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
        partCategory = getPartSubCategoryList(url);
        return partCategory;

    }

    public List<PartCategory> getPartCategoryListByIdFromCarType(String id) throws IOException {
        List<Type> element = carType.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
       if (element.isEmpty()) {
            return new ArrayList<>();
        }
        String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
        partCategory = getPartCategoryList(url);
        return partCategory;
    }

    public List<PartCategory> getPartCategoryListByIdForTerminal(String id) throws IOException {
        if (breadcrumbs.size() < 3) {
            List<Type> element = carType.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
            String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
            partCategory = getPartCategoryList(url);
        } else {
            List<PartCategory> element = getElementFromList(id);
            String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
            partCategory = getPartSubCategoryList(url);
           }
        return partCategory;
    }

    public List<Part> getPartListById(String id) throws IOException {

        List<PartCategory> element = getElementFromList(id);
        if (element.isEmpty()) {
            return new ArrayList<>();

        }
        String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "/stock?lang=polish";
        part = getPartList(url);
        return part;
    }

    private List<PartCategory> getElementFromList(String id) {
        return partCategory.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
    }
//    private <T> T getElementFromList(List<T implements filtrablebyid > el, String id) {
//        return el.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
//    }
}
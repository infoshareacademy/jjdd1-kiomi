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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CarsDataLoader2 {
    final String DEFAULT_URL = "http://infoshareacademycom.2find.ru/api/v2?lang=polish";
    private static final int FIRST_ELEMENT = 0;
    private static final Logger LOGGER = LogManager.getLogger(CarsDataLoader2.class);

    private static String JSON_DATA_TAG = "data";

    @Inject
    BrandsCache brandsCache;

    static List<Brand> brand = new ArrayList();
    static List<Model> model = new ArrayList();
    static List<Type> carType = new ArrayList();
    static List<PartCategory> partCategory = new ArrayList();
    static List<Part> part = new ArrayList();
    private final

    static <T> T jsonLoader(T c, String file) throws IOException {
        Gson gson = new Gson();
        InputStream is = new URL(file).openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        JsonObject response = gson.fromJson(bufferedReader, JsonObject.class);
        JsonElement data = response.get(JSON_DATA_TAG);

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

    public List<Model> getModelsListBylink(String link) throws IOException {
        model = jsonLoader(model, link);

        LOGGER.debug("Liczba wyników wyszukiwania po id: " + model.size());
        if (!model.isEmpty()) {
            LOGGER.debug(String.format("Pierwszy element z listy wyszukanych modeli samochodów po id %s", model.get(FIRST_ELEMENT)));
        }
        return model;
    }

    public List<Type> getTypesListByLink(String link) throws IOException {
        carType = jsonLoader(carType, link);

//        LOGGER.debug("Ilość wyników wyszukiwania po typie samochodu: " + carType.size());
//        LOGGER.debug(String.format("Pierwszy element z listy wyszukiwania po typie samochodu %s", carType.get(FIRST_ELEMENT)));

//        LOGGER.debug("Ilość wyników wyszukiwania po wyborze typu: " + carType.size());
//        LOGGER.debug(String.format("Pierwszy element z listy wyszukanych typów %s", carType.get(FIRST_ELEMENT)));
        return carType;

    }

    public List<PartCategory> getPartCategoryListByLink(String link) throws IOException {
        partCategory = jsonLoader(partCategory, link);
        return partCategory;
    }

    public List<Part> getPartListByLink(String link) throws IOException {
        part = jsonLoader(part, link);
        return part;
    }
}
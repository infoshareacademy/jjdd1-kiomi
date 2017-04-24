package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

//@ApplicationScoped
@Stateless
public class CarsDataLoader {
    final String DEFAULT_URL = "http://infoshareacademycom.2find.ru/api/v2?lang=polish";
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

//    @Inject
//    BrandsCache brandsCache;

    static List<Brand> brand = new ArrayList();
    static List<Model> model = new ArrayList();
    static List<Type> carType = new ArrayList();
    static List<PartCategory> partCategory = new ArrayList();
    static List<Part> part = new ArrayList();
    static List<BreadcrumbsBuilder> breadcrumbs = new ArrayList();
    private static final Logger LOGGER = LoggerFactory.getLogger(CarsDataLoader.class);

    static <T> T jsonLoader(T c, String file) throws IOException {
//        try {
        Gson gson = new Gson();
//
//            Path root = Paths.get(System.getProperty("java.io.tmpdir")).resolve(RESOURCES_DIR);
////            System.out.println(root);
//            byte[] bytes = Files.readAllBytes(root.resolve(file));
//            BufferedReader bufferedReader = Files.newBufferedReader(root.resolve(file));
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

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return c;
    }

    public List<Brand> getBrandsList() throws IOException {
//        BrandsCache brandsCache = new BrandsCache();
//        brand = brandsCache.getBrandsList();
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

    public static String getDataFromBreadcrumbs() {
        String[] links = breadcrumbs.get(breadcrumbs.size() - 1).getLink().split("/");

        return (links[links.length - 1].equals("stock")) ? links[links.length - 2] : links[links.length - 1];
    }

    public List<Model> getModelsListById(String id) throws IOException {
BrandsCache brandsCache=new BrandsCache();
        brand = brandsCache.getBrandsList();
        List<Brand> element = brand.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if(element.isEmpty()) {
            return new ArrayList<>();
        }
        String link=Optional.ofNullable(element.get(0).getLink()).orElse("");
        String url = "http://infoshareacademycom.2find.ru" + link + "?lang=polish";
        model = getModelsList(url);

        List<Model> temporaryModel = new ArrayList<Model>();
        for (Model x : model) {
            String[] links = x.getLink().split("/");

            if (links[4].equals(id)) {
                temporaryModel.add(x);
            }

        }
        LOGGER.debug("Liczba wyników wyszukiwania po id: " + temporaryModel.size());
        if (!temporaryModel.isEmpty()) {
            LOGGER.debug(String.format("Pierwszy element z listy wyszukanych modeli samochodów po id %s", temporaryModel.get(FIRST_ELEMENT)));
        }

        return temporaryModel;
    }

    public List<Type> getTypesListById(String id) throws IOException {
        List<Model> element = model.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if(element.isEmpty()) {
            return new ArrayList<>();
        }
        String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
        carType = getCarTypesList(url);

        LOGGER.debug("Ilość wyników wyszukiwania po typie samochodu: " + carType.size());
        LOGGER.debug(String.format("Pierwszy element z listy wyszukiwania po typie samochodu %s", carType.get(FIRST_ELEMENT)));


        List<Type> temporaryType = new ArrayList<Type>();

        for (Type x : carType) {
            String[] links = x.getLink().split("/");
            if (links[5].equals(id)) {
                temporaryType.add(x);
            }

        }

        LOGGER.debug("Ilość wyników wyszukiwania po wyborze typu: " + temporaryType.size());
        LOGGER.debug(String.format("Pierwszy element z listy wyszukanych typów %s", temporaryType.get(FIRST_ELEMENT)));
        return temporaryType;

    }

    public List<PartCategory> getPartCategoryListByIdFromPartCategory(String id) throws IOException {
        List<PartCategory> element = partCategory.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if(element.isEmpty()) {
            return new ArrayList<>();
        }
        String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
        partCategory = getPartSubCategoryList(url);
        return getPartCategoryListById(id);
    }

    public List<PartCategory> getPartCategoryListByIdFromCarType(String id) throws IOException {
        List<Type> element = carType.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if(element.isEmpty()) {
            return new ArrayList<>();
        }
        String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
        partCategory = getPartCategoryList(url);
        return getPartCategoryListById(id);
    }

    public List<PartCategory> getPartCategoryListByIdForTerminal(String id) throws IOException {
        if (breadcrumbs.size() < 3) {
            List<Type> element = carType.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
            String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
            partCategory = getPartCategoryList(url);
            System.out.println(url);
        } else {
            List<PartCategory> element = partCategory.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
            String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "?lang=polish";
            partCategory = getPartSubCategoryList(url);
            System.out.println(url);
        }
        System.out.println(partCategory);
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

        System.out.println(id);
        List<PartCategory> element = partCategory.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if(element.isEmpty()) {
            return new ArrayList<>();
        }
        String url = "http://infoshareacademycom.2find.ru" + element.get(0).getLink() + "/stock?lang=polish";
        part = getPartList(url);


        List<Part> temporaryPart = new ArrayList<Part>();

        for (Part x : part) {
            if (getDataFromBreadcrumbs().equals(id)) {
                temporaryPart.add(x);
            }

        }

        return temporaryPart;

    }


}



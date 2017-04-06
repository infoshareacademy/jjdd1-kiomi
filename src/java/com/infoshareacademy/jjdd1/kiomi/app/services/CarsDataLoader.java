package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.PartCategory;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Type;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CarsDataLoader {
    private static final String BRANDS_URI = "src/Resources/brands.json";
    private static final String MODELS_URI = "src/Resources/FORD - modele.json";
    private static final String PART_URI = "src/Resources/FORD FOCUS III 1.5 TDCi XXDA - czйШci - hamulce - tarczowe.json";
    private static final String PARTCATEGORY_URI = "src/Resources/FORD FOCUS III 1.5 TDCi XXDA - czйШci.json";

    private String URI = "/FORD FOCUS III - typy.json";

    Set<Brand> brand = new HashSet();
    Set<Model> model = new HashSet();
    Set<Type> type = new HashSet();
    Set<PartCategory> partcategory = new HashSet();

    public static void main(String[] args) throws IOException {
        loadData();
//        System.out.println(loadData());
    }

    public static void loadData() throws IOException {
//        Gson gson = new Gson();
//        try {
        java.lang.reflect.Type listType = new TypeToken<List<Brand>>() {
        }.getType();
        JsonReader jsonReader = new JsonReader(new FileReader(BRANDS_URI));
        jsonReader.beginObject();

        while (jsonReader.hasNext()) {

            String name = jsonReader.nextName();
            if (name.equals("data")) {
                readApp(jsonReader);

            }
        }

        jsonReader.endObject();
        jsonReader.close();
//            List<Brand> parsed = gson.fromJson(fileReader, listType);
//            return parsed.toString();
//            for(Brand parse:parsed) {
//                return parse.ge;
//            }

//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public static void readApp(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            System.out.println(name);
            if (name.contains("app")) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String n = jsonReader.nextName();
                    if (n.equals("name")) {
                        System.out.println(jsonReader.nextString());
                    }
                    if (n.equals("age")) {
                        System.out.println(jsonReader.nextInt());
                    }
                    if (n.equals("messages")) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            System.out.println(jsonReader.nextString());
                        }
                        jsonReader.endArray();
                    }
                }
                jsonReader.endObject();
            }

        }
        jsonReader.endObject();
    }
}



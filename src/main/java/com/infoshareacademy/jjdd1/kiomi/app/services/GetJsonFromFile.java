package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.CarFromAztecJson;

import javax.validation.constraints.Null;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader2.RESOURCES_DIR;

/**
 * Created by arek50 on 2017-04-27.
 */
public class GetJsonFromFile {
    private static Path aztecJson = Paths.get("AztecCodeResult.json");
    private String JSON_DATA_TAG = "Dane";

    public CarFromAztecJson getJsonFile() {
        Gson gson = new Gson();
        try {
            Path root = Paths.get(System.getProperty("java.io.tmpdir")).resolve(RESOURCES_DIR);
//            byte[] bytes = Files.readAllBytes(root.resolve(aztecJson));
            BufferedReader bufferedReader = Files.newBufferedReader(root.resolve(aztecJson));
            JsonObject response = gson.fromJson(bufferedReader, JsonObject.class);
            JsonElement data = response.get(JSON_DATA_TAG);

            return gson.fromJson(data, new TypeToken<CarFromAztecJson>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

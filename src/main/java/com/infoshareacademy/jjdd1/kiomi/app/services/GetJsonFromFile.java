package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.CarFromAztecJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by arek50 on 2017-04-27.
 */
public class GetJsonFromFile {
    private static String aztecJson ="AztecCodeResult.json";
    private String JSON_DATA_TAG = "Dane";


    public CarFromAztecJson getJsonFile(String code) throws IOException {
        String aztecCode=(code.length()>5)?code:aztecJson;

        Gson gson = new Gson();
        //            Path root = Paths.get(System.getProperty("java.io.tmpdir")).resolve(RESOURCES_DIR);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(

                this.getClass().getResourceAsStream("/" + aztecCode)));

        AztecConfiguration aztecConfiguration=new AztecConfiguration();
        BufferedReader bReader=aztecConfiguration.replaceFromMap(bufferedReader);

        JsonObject response = gson.fromJson(bReader, JsonObject.class);

        JsonElement data = response.get(JSON_DATA_TAG);

        return gson.fromJson(data, new TypeToken<CarFromAztecJson>() {
        }.getType());

//        return null;
    }
}

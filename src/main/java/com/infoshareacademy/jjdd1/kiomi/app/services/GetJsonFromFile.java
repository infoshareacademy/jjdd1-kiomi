package com.infoshareacademy.jjdd1.kiomi.app.services;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by arek50 on 2017-04-27.
 */
public class GetJsonFromFile {
    private static Path aztecJson = Paths.get("AztecCodeResult.json");

    public String getJsonFile() {
        Path root = Paths.get(System.getProperty("java.io.tmpdir")).resolve(CarsDataLoader.RESOURCES_DIR);


        return "xxx";
    }
}

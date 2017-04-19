
package com.infoshareacademy.jjdd1.kiomi.app.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class PromotedBrandsLoader {

    private static List<String> promotedBrandsList;
    private static Path promotedBrandsPath = Paths.get("promotedBrands.txt");
    private static Scanner clientBrandsPicker;

    public static List<String> promotedBrandsReader() {
        try {
            Path root = Paths.get(System.getProperty("java.io.tmpdir")).resolve(CarsDataLoader.RESOURCES_DIR);
            System.out.println(root);
            promotedBrandsList = Files.readAllLines(root.resolve(promotedBrandsPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return promotedBrandsList;
    }
}
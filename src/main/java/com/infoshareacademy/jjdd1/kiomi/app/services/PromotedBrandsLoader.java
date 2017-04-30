
package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class PromotedBrandsLoader {

    private static List<String> promotedBrandsList = new ArrayList<>();
    private static String promotedBrandsPath = "promotedBrands.txt";
    static final String RESOURCES_DIR = "kiomi";

    static List<Part> rewritedPartList = new ArrayList();

    public static List<String> getPromotedBrandsList() {
        return promotedBrandsList;
    }

    public List<String> promotedBrandsReader() throws IOException {
        try {

//            Path root = Paths.get(System.getProperty("java.io.tmpdir")).resolve(CarsDataLoader.RESOURCES_DIR);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("/" + promotedBrandsPath)));
            promotedBrandsList = Files.readAllLines((Path) bufferedReader);
        } catch (Exception e) {

        }
        return promotedBrandsList;
    }

    public List<Part> rewritedPartListSorter(List<Part> originalPartList) throws IOException {

        rewritedPartList.clear();
        promotedBrandsList = promotedBrandsReader();
        if (promotedBrandsList.size() > 0) {
            for (String promotedBrand : promotedBrandsList) {
                for (Part originalPartFromList : originalPartList) {
                    if (promotedBrand.equals(originalPartFromList.getBrand_clear())) {
                        rewritedPartList.add(originalPartFromList);
                    }
                }
            }
            for (String promotedBrand : promotedBrandsList) {
                for (Part originalPartFromList : originalPartList) {
                    if (!promotedBrand.equals(originalPartFromList.getBrand_clear())) {
                        rewritedPartList.add(originalPartFromList);
                    }
                }
            }
            return rewritedPartList;
        }
        return originalPartList;
    }

}

package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PromotedBrandsLoader {

    private static List<String> promotedBrandsList;
    private static Path promotedBrandsPath = Paths.get("promotedBrands.txt");
    static List<Part> rewritedPartList = new ArrayList();

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

    public static List<Part> rewritedPartListSorter (List<Part> originalPartList) {

        for (String promotedBrand : promotedBrandsList)
            for (Part originalPartFromList : originalPartList)
                if (promotedBrand.equals(originalPartFromList.getBrand_clear()))
                    rewritedPartList.add(originalPartFromList);

        for (String promotedBrand : promotedBrandsList)
            for (Part originalPartFromList : originalPartList)
                if (!promotedBrand.equals(originalPartFromList.getBrand_clear()))
                    rewritedPartList.add(originalPartFromList);

        return rewritedPartList;
    }

}
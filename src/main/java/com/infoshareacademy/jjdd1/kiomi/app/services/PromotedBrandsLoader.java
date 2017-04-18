package com.infoshareacademy.jjdd1.kiomi.app.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class PromotedBrandsLoader {

    private static List<String> promotedBrandsList;
    private static Path promotedBrandsPath = Paths.get("src/main/resources/promotedBrands.txt");
    private static Scanner clientBrandsPicker;

    public void promotedBrandsReader () throws IOException {
        promotedBrandsList = Files.readAllLines(promotedBrandsPath);
        System.out.println(promotedBrandsList);
    }

}

package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Startup
@Singleton
public class BrandsCache {
    private List<Brand> brandsList = new ArrayList<>();

    @Inject
    CarsDataLoader jsonParser;


    @Schedule(minute = "*", hour = "*/1")
    @PostConstruct
    public void starter() {

        try {
            brandsList = jsonParser.getBrandsList();
            System.out.println(brandsList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //chyba jakiś loger o stanie cache
    }

    public List<Brand> getBrandsList() {
        return brandsList;
    }
}
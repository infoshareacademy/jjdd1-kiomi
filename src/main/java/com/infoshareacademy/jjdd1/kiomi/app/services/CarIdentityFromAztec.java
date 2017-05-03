package com.infoshareacademy.jjdd1.kiomi.app.services;


import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by arek50 on 2017-04-22.
 */
@Stateless
public class CarIdentityFromAztec {

    @Inject
    BrandsCache brandsCache;

    public CarIdentityFromAztec() {
    }

    public Car FindingCarByAztecCode(CarFromAztecJson aztecCode) throws IOException {
        Car myCar = new Car();
        Brand brand = findBrand(aztecCode.getBrand());

        Model model = findModel(brand.getLink(), aztecCode.getModel(), aztecCode.getProductionYear());

        myCar.setBrand(brand);
        myCar.setModel(model);
        return myCar;
    }

    private Brand findBrand(String brandFromAztec) {

//brandsCache=new BrandsCache();
        List<Brand> brands = brandsCache.getBrandsList();
        //LOGGER:
        System.out.println(brandsCache.getBrandsList());
        for (Brand brand : brands) {
            if (brand.getName().contains(brandFromAztec)) {
                return brand;
            }
        }
        return null;
    }

    private Model findModel(String link, String modelFromAztec, String yearProduction) throws IOException {
        CarsDataLoader2 jsonParser = new CarsDataLoader2();
        //logger:
        String url = "http://infoshareacademycom.2find.ru" + link + "?lang=polish";
        List<Model> modelsList = jsonParser.getModelsListBylink(url);

        Integer startYear;
        Integer endYear;
        Integer productionYear = Integer.parseInt(yearProduction);
        for (Model model : modelsList) {
            startYear = Integer.valueOf(model.getStart_year());
            endYear = (model.getEnd_year() == null) ?
                    LocalDateTime.now().getYear() : Integer.valueOf(model.getEnd_year());

            if (model.getName().toUpperCase().contains(modelFromAztec.toUpperCase().toString()) && startYear <= productionYear && endYear >= productionYear) {
                //Loger mam model xxx
                return model;
            }
        }
//Loger modelu brak
        return null;
    }
}

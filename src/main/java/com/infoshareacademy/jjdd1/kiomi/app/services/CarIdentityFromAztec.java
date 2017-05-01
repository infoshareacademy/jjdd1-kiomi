package com.infoshareacademy.jjdd1.kiomi.app.services;


import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        try {
            Brand brand = findBrand(aztecCode.getBrand());
            Model model=findModel(brand.getLink(), aztecCode.getModel(), aztecCode.getProductionYear());
            myCar.setBrand(brand);
            myCar.setModel(model);
            return myCar;
        } catch (NumberFormatException e) {

        }
        return myCar;
    }

    private Brand findBrand(String brandFromAztec) {

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

    public List<Type> findCarType(String url, CarFromAztecJson dataFromAztec) throws IOException {

        CarsDataLoader2 jsonParser = new CarsDataLoader2();
        //logger:
        List<Type> carTypesList = jsonParser.getTypesListByLink(url);
        System.out.println(url);
        return searchCarTypeByFields(carTypesList, dataFromAztec.getCarCapacity(), dataFromAztec.getCarFuelType(), dataFromAztec.getCarPower());
    }

    public List<Type> searchCarTypeByFields(
            List<Type> carTypesList, String carCapacity, String carFuelType, String carPower) {

        int carCapacityAsInt = Integer.parseInt(carCapacity.substring(0, carCapacity.length() - 6));
        int powerAsInt = Integer.parseInt(carPower.substring(0, carCapacity.length() - 8));

        String fuelTypeAsText;
        switch (carFuelType) {
            case "P":
                fuelTypeAsText = "benzyna";
                break;
            case "D":
                fuelTypeAsText = "olej napędowy";
                break;
            default:
                fuelTypeAsText = "brak";
                break;
        }

        return carTypesList.stream()
                .filter(t -> Math.abs(t.getCcm() - carCapacityAsInt) <= 15)
                .filter(t -> Math.abs(t.getKw() - powerAsInt) <= 15)
                .filter(t -> t.getFuel().equals(fuelTypeAsText))
                .collect(Collectors.toList());

    }
}

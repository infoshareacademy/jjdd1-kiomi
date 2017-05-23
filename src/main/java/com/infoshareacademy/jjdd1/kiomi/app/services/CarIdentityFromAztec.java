package com.infoshareacademy.jjdd1.kiomi.app.services;


import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arek50 on 2017-04-22.
 */
@Stateless
public class CarIdentityFromAztec {
    private static final Logger LOGGER = LogManager.getLogger(CarIdentityFromAztec.class);

    @Inject
    BrandsCache brandsCache;
    @Inject
    SessionData sessionData;
    private static final String ERROR_SESSION_FROM_ATENA = "0";

    public CarIdentityFromAztec() {
    }

    public Car FindingCarByAztecCode(CarFromAztecJson aztecCode) throws IOException {
        Car myCar = new Car();

        if (!(aztecCode.getError()).equals(ERROR_SESSION_FROM_ATENA)) {
            LOGGER.debug("We haven't session key");
            return myCar;
        }

        try {
            if (aztecCode.getBrand().equals("")) {
                return myCar;
            }
            Brand brand = findBrand(aztecCode.getBrand());
            if (brand == null) {
                return myCar;
            }
            List<Model> model = findModel(brand.getLink(), aztecCode.getModel(), aztecCode.getProductionYear());
            myCar.setBrand(brand);
            sessionData.setCar(myCar);
            if (model == null) {
                return myCar;
            }
            if (model.size() == 1) {
                myCar.setModel(model.get(0));
                myCar.setBrand(sessionData.getCar().getBrand());
                sessionData.setCar(myCar);
            }
            return myCar;
        } catch (NumberFormatException e) {

        }

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

    public List<Model> findModel(String link, String modelFromAztec, String yearProduction) throws IOException {
        CarsDataLoader2 jsonParser = new CarsDataLoader2();
        //logger:
        String url = "http://infoshareacademycom.2find.ru" + link + "?lang=polish";
        List<Model> modelsList = jsonParser.getModelsListBylink(url);

        if (yearProduction==null || modelFromAztec==null) {
            LOGGER.debug("We haven't session key");
            return modelsList;
        }
        Integer startYear;
        Integer endYear;
        Integer productionYear = Integer.parseInt(yearProduction);
        List<Model> mlist = new ArrayList<>();
        for (Model model : modelsList) {
            startYear = Integer.valueOf(model.getStart_year());
            endYear = (model.getEnd_year() == null) ?
                    LocalDateTime.now().getYear() : Integer.valueOf(model.getEnd_year());

            if (model.getName().toUpperCase().contains(modelFromAztec.toUpperCase().toString()) && (startYear <= productionYear && endYear >= productionYear)) {
                //Loger mam model xxx
//                return model;
                mlist.add(model);
            }

        }

//Loger modelu brak
        return mlist;
//        return null;
    }


    public List<Type> findCarType(String url, CarFromAztecJson dataFromAztec) throws IOException {

        CarsDataLoader2 jsonParser = new CarsDataLoader2();
        //logger:
        List<Type> carTypesList = jsonParser.getTypesListByLink(url);
        if (dataFromAztec.getCarCapacity()==null) {
            LOGGER.debug("We haven't session key");
            return carTypesList;
        }

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
                .filter(t -> (t.getCcm() - carCapacityAsInt) == 0)
                .filter(t -> (t.getKw() - powerAsInt) == 0)
                .filter(t -> t.getFuel().equals(fuelTypeAsText))
                .collect(Collectors.toList());

    }

}

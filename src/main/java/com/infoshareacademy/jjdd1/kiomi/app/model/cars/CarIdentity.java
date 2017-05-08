package com.infoshareacademy.jjdd1.kiomi.app.model.cars;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.ejb.Stateless;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


//@Stateless
public class CarIdentity {
    private static final Logger LOGGER = LogManager.getLogger(CarIdentity.class);

    public void findCarByUri(Map<String, String[]> parameters) throws IOException {
        Car myCar = new Car();
        BrandsCache brandsCache = new BrandsCache();
        List<Brand> brandsCollection = brandsCache.getBrandsList();

        String[] b = Optional.ofNullable(parameters.get("brand")).orElse(new String[]{""});
        List<Brand> cos = brandsCollection.stream().filter(p -> p.getId().equals(b[0])).collect(Collectors.toList());
        myCar.setBrand(cos.get(0));
        System.out.println(myCar.getBrand());

    }

}

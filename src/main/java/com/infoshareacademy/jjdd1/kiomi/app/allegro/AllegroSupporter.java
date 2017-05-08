package com.infoshareacademy.jjdd1.kiomi.app.allegro;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Part;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.PartCategory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class AllegroSupporter {
String test;//do usunięcia
    private static final Logger LOGGER = LogManager.getLogger(AllegroSupporter.class);
    public String generateQuery(Part part) {
return String.valueOf(part);
    }
    public String genereteQueryFind(Part part) {
        return String.valueOf(part);
    }
    public String findCategories(Part part, PartCategory partCategory) {
        return test;
    }
    public String getQueriesWithCategories(Part part, PartCategory partCategory) {
        return test;
    }
}

package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader2;

import com.infoshareacademy.jjdd1.kiomi.app.services.MailSender;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.StatisticDataBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arek50 on 2017-04-22.
 */
@Startup
@Singleton
public class BrandsCache {
    private final Logger LOGGER = LogManager.getLogger(BrandsCache.class);
    private List<Brand> brandsList = new ArrayList<>();

    @Inject
    CarsDataLoader2 jsonParser;

    @Schedule(minute = "*", hour = "1")
    @PostConstruct
    public void starter() {

        MailSender mailSender =new MailSender();
        mailSender.run();

//        StatisticDataBuilder statisticDataBuilder = new StatisticDataBuilder();
//        statisticDataBuilder.buildEntryToDatabase();

        try {
            brandsList = jsonParser.getBrandsList();
            LOGGER.info("Number of brandList elements: "+brandsList.size());

        } catch (IOException e) {
            LOGGER.error("Error occurred while loading brandList.", e.getMessage());
        }
    }

    public List<Brand> getBrandsList() {
        return brandsList;
    }
}
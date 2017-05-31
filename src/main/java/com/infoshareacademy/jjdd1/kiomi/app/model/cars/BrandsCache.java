package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader2;

import com.infoshareacademy.jjdd1.kiomi.app.services.MailSender;
import com.infoshareacademy.jjdd1.kiomi.app.services.MembersDataBuilder;
import com.infoshareacademy.jjdd1.kiomi.app.services.users.UsersList;


//import com.infoshareacademy.jjdd1.kiomi.app.services.MailSender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    @Inject
    MembersDataBuilder membersDataBuilder;

    @Schedule(minute = "*", hour = "1")
    @PostConstruct
    public void starter() {
        UsersList member = new UsersList();
        member.setEntryDate(new Date());
        member.setEmail("azielazny@gmail.com");
        member.setFirstname("Arkadiusz");
        member.setLastname("Zielazny");
        member.setRole(1);
        LOGGER.debug("Creating new entity object: " + member.toString());
        membersDataBuilder.addEntryToDatabase(member);
        UsersList member2 = new UsersList();
        member2.setEntryDate(new Date());
        member2.setEmail("kiomi.info@gmail.com");
        member2.setFirstname("Kiomi");
        member2.setLastname("Kiomi");
        member2.setRole(0);
        LOGGER.debug("Creating new entity object: " + member2.toString());
        membersDataBuilder.addEntryToDatabase(member2);
        UsersList member3 = new UsersList();
        member3.setEntryDate(new Date());
        member3.setEmail("isaloginjava@gmail.com");
        member3.setFirstname("Infoshare");
        member3.setLastname("Academy");
        member3.setRole(1);
        LOGGER.debug("Creating new entity object: " + member3.toString());
        membersDataBuilder.addEntryToDatabase(member3);




        MailSender mailSender = new MailSender();
        mailSender.run();




        try {
            brandsList = jsonParser.getBrandsList();
            LOGGER.info("Number of brandList elements: " + brandsList.size());

        } catch (IOException e) {
            LOGGER.error("Error occurred while loading brandList.", e.getMessage());
        }
    }

    public List<Brand> getBrandsList() {
        return brandsList;
    }
}
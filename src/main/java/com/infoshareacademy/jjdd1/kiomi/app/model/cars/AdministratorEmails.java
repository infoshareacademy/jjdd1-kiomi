package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by arek50 on 2017-04-27.
 */
public class AdministratorEmails {

    private static final Logger LOGGER = LogManager.getLogger(AdministratorEmails.class);
    private static List<String> emailsList = new ArrayList<String>() {
        {
            add("azielazny@gmail.com");
            add("marcinpplonka@gmail.com");
            add("okraszewski.m@gmail.com");
            add("kiomi.info@gmail.com");
        }
    };

    public static int isAdministrator(String email) {
        return emailsList.contains(email) ? 1 : 0;
    }
}

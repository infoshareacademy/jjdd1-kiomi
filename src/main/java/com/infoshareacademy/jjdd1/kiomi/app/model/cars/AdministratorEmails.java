package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arek50 on 2017-04-27.
 */
public class AdministratorEmails {

    private static List<String> emailsList = new ArrayList<String>() {
        {
            add("azielazny@gmail.com");
        }
    };

    public static int isAdministrator(String email) {
        return emailsList.contains(email) ? 1 : 0;
    }
}

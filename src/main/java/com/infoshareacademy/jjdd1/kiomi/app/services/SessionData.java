package com.infoshareacademy.jjdd1.kiomi.app.services;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by arek50 on 2017-05-01.
 */
@Named
@SessionScoped
public class SessionData implements Serializable {

    private boolean logged = false;
    private String username;

    public void logUser(String username) {
        this.username = username;
        this.logged = true;
    }

    public boolean isLogged() {
        return logged;
    }

    public String getUsername() {
        return username;
    }
    public void logout() {
        username = null;
        logged=false;

    }
}

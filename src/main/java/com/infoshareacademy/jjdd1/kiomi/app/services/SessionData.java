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

    private String firstname;
    private String lastname;
    private String picture;
    private String email;


    private boolean logged = false;


    public void logUser(String firstname, String lastname, String picture, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.picture = picture;
        this.email = email;
        this.logged = true;
    }

    public boolean isLogged() {
        return logged;
    }


    public void logout() {
        firstname = null;
        lastname=null;
        picture=null;
        email=null;
        logged = false;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }
}

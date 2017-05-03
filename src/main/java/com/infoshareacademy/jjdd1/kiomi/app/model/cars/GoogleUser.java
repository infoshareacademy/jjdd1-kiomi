package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

/**
 * Created by arek50 on 2017-05-03.
 { "id": "106158523958783598341", "email": "azielazny@gmail.com", "verified_email": true, "name": "Arkadiusz Zielazny", "given_name": "Arkadiusz", "family_name": "Zielazny", "link": "https://plus.google.com/106158523958783598341", "picture": "https://lh6.googleusercontent.com/-MbnahePPX7U/AAAAAAAAAAI/AAAAAAAAAJM/bw73uX93KPo/photo.jpg", "gender": "male" }
 */
public class GoogleUser {

    private String given_name;
    private String family_name;
    private String picture;
    private String email;

    public String getGiven_name() {
        return given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public String getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }
}

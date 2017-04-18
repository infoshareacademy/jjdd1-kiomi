package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

/**
 * example data:
 * data:brands
 * "id": "8ro",
 * "name": "ABARTH",
 * "name_clear": "abarth",
 * "has_image": false,
 * "link": "\/api\/v2\/find\/8ro"
 */
public class Brand {
    private String id;
    private String name;
    private String name_clear;
    private Boolean has_image;
    private String link;


    public Brand(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_clear() {
        return name_clear;
    }

    public void setName_clear(String name_clear) {
        this.name_clear = name_clear;
    }

    public Boolean getHas_image() {
        return has_image;
    }

    public void setHas_image(Boolean has_image) {
        this.has_image = has_image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}

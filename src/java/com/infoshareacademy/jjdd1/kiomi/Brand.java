package com.infoshareacademy.jjdd1.kiomi;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, String> identificator = new HashMap();//klucz id, wartość tablica[reszta]
    private String uri;
}

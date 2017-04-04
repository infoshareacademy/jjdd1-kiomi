package com.infoshareacademy.jjdd1.kiomi;

import java.util.HashMap;
import java.util.Map;

/**
 * example data
 * "id": "7pz",
 * "name": "5000-Serie",
 * "end_year": "1993",
 * "end_month": "12",
 * "start_year": "1985",
 * "start_month": "01",
 * "vehicle_group": "commercial",
 * "link": "\/api\/v2\/find\/ey\/7pz"
 */
public class CarModel extends Brand {
    private String id;
    private String name;
    private String end_year;
    private String end_month;
    private String start_year;
    private String start_month;
    private String vehicle_group;
    private String link;
    private String uri;
    private Map<String, String> identificator = new HashMap();//klucz id, wartość tablica[reszta]


}

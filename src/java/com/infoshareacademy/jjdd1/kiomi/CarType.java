package com.infoshareacademy.jjdd1.kiomi;

import java.util.HashMap;
import java.util.Map;

/**
 * example data:
 *
 * "id": "mtn",
 * "model_id": "72o",
 * "brand_id": "ey",
 * "name": "1.0 EcoBoost",
 * "end_year": null,
 * "end_month": null,
 * "start_year": "2012",
 * "start_month": "02",
 * "ccm": "998",
 * "kw": "92",
 * "hp": "125",
 * "cylinders": "3",
 * "engine": "M1DA",
 * "engine_txt": "Silnik benzynowy",
 * "fuel": "benzyna",
 * "body": "hatchback",
 * "axle": null,
 * "max_weight": null,
 * "link": "\/api\/v2\/find\/ey\/72o\/mtn"
 */
public class CarType extends CarModel {
    private String id;
    private String model_id;
    private String brand_id;
    private String name;
    private int end_year;
    private String end_month;
    private String start_month;
    private int ccm;
    private int kw;
    private int cylinders;
    private int engine;
    private String engine_txt;
    private String fuel;
    private String body;
    private String axle;
    private String max_weight;
    private String link;
    private String uri;
    private Map<String, String> identificator = new HashMap();//klucz id, wartość tablica[reszta]

}

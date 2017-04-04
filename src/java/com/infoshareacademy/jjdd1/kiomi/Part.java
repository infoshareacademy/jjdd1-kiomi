package com.infoshareacademy.jjdd1.kiomi;

import java.util.HashMap;
import java.util.Map;

/**
 * "brand": "TRW",
 * "brand_clear": "trw",
 * "number": "GCH666",
 * "number_clear": "gch666",
 * "name": "Ci\u0119gno, hamulec postojowy",
 * "status": "Normal",
 * "link": "\/api\/v2\/part\/trw\/gch666"
 */
public class Part extends CategoryParts {

    private String brand;
    private String brand_clear;
    private String number;
    private String number_clear;
    private String name;
    private String status;
    private String link;
    private String uri;
    private Map<String, String> identificator = new HashMap();//klucz id, wartość tablica[reszta]

}

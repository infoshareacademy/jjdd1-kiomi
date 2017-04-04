package com.infoshareacademy.jjdd1.kiomi;

import java.util.HashMap;
import java.util.Map;

/**
 * "id": "7sy",
 * "name": "Nadwozie",
 * "has_children": true,
 * "link": "\/api\/v2\/find\/ey\/72o\/2b91\/7sy"
 */
public class CategoryParts extends Brand {
    private long id;
    private String name;
    private boolean has_children;
    private String link;
    private String uri;
    private Map<String, String> identificator = new HashMap();//klucz id, wartość tablica[reszta]

}

package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * "BRAND": "TRW",
 * "brand_clear": "trw",
 * "number": "GCH666",
 * "number_clear": "gch666",
 * "name": "Ci\u0119gno, hamulec postojowy",
 * "status": "Normal",
 * "link": "\/api\/v2\/part\/trw\/gch666"
 */
public class Part {
    private String brand;
    private String brand_clear;
    private String number;
    private String number_clear;
    private String name;
    private String status;
    private String link;
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Part.class);

    public Part(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand_clear() {
        return brand_clear;
    }

    public void setBrand_clear(String brand_clear) {
        this.brand_clear = brand_clear;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber_clear() {
        return number_clear;
    }

    public void setNumber_clear(String number_clear) {
        this.number_clear = number_clear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return name+ "(Marka: "+brand+")"+
                "\n     Kod Wew.: "+number+
                "     status: "+status;
    }
}

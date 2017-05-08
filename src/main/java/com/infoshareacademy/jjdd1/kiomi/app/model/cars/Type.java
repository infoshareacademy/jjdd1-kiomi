package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

import com.infoshareacademy.jjdd1.kiomi.TerminalMenu;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
public class Type {
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
    private String engine;
    private String engine_txt;
    private String fuel;
    private String body;
    private String axle;
    private String max_weight;
    private String link;
    private static final Logger LOGGER = LogManager.getLogger(Type.class);

    public Type(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnd_year() {
        return end_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    public String getEnd_month() {
        return end_month;
    }

    public void setEnd_month(String end_month) {
        this.end_month = end_month;
    }

    public String getStart_month() {
        return start_month;
    }

    public void setStart_month(String start_month) {
        this.start_month = start_month;
    }

    public int getCcm() {
        return ccm;
    }

    public void setCcm(int ccm) {
        this.ccm = ccm;
    }

    public int getKw() {
        return kw;
    }

    public void setKw(int kw) {
        this.kw = kw;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngine_txt() {
        return engine_txt;
    }

    public void setEngine_txt(String engine_txt) {
        this.engine_txt = engine_txt;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAxle() {
        return axle;
    }

    public void setAxle(String axle) {
        this.axle = axle;
    }

    public String getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(String max_weight) {
        this.max_weight = max_weight;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return   name +" (" +id +
                ") ";
    }
}

package com.infoshareacademy.jjdd1.kiomi.app.statistics;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "search_history")
public class Statistics implements Serializable{
    private static final Logger LOGGER = LogManager.getLogger(Statistics.class);

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;


    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date entryDate;

    @Column(name = "car_brand", length = 50)
    private String carBrand;

    @Column(name = "car_model", length = 50)
    private String carModel;

    @Column(name = "car_type", length = 50)
    private String carType;

    @Column(name = "part_category", length = 50)
    private String partCategory;

    @Column(name = "part_brand", length = 50)
    private String partBrand;

    @Column(name = "part_name", length = 255)
    private String partName;

    @Column(name = "part_serial", length = 50)
    private String partSerial;

    @Column(name = "promoted")
    private boolean promoted;

    public Statistics() {
    }

    public Statistics setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public Statistics setCarBrand(String carBrand) {
        this.carBrand = carBrand;
        return this;
    }

    public Statistics setCarModel(String carModel) {
        this.carModel = carModel;
        return this;
    }

    public Statistics setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public Statistics setPartCategory(String partCategory) {
        this.partCategory = partCategory;
        return this;
    }

    public Statistics setPartBrand(String partBrand) {
        this.partBrand = partBrand;
        return this;
    }

    public Statistics setPartName(String partName) {
        this.partName = partName;
        return this;
    }

    public Statistics setPartSerial(String partSerial) {
        this.partSerial = partSerial;
        return this;
    }

    public Statistics setPromoted(boolean promoted) {
        this.promoted = promoted;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarType() {
        return carType;
    }

    public String getPartCategory() {
        return partCategory;
    }

    public String getPartBrand() {
        return partBrand;
    }

    public String getPartName() {
        return partName;
    }

    public String getPartSerial() {
        return partSerial;
    }

    public boolean isPromoted() {
        return promoted;
    }
}

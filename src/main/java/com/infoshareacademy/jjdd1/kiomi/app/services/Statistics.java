package com.infoshareacademy.jjdd1.kiomi.app.services;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by marcin on 22.04.17.
 */

@Entity
@Table
public class Statistics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)

    private Date createTime;

    @Column(nullable = false)
    private String carBrand;


    private String partCategory;


    private String partBrand;


    private String partSerial;

    @Column(nullable = false)
    private boolean promoted;



    public Statistics() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getPartBrand() {
        return partBrand;
    }

    public void setPartBrand(String partBrand) {
        this.partBrand = partBrand;
    }

    public String getPartSerial() {
        return partSerial;
    }

    public void setPartSerial(String partSerial) {
        this.partSerial = partSerial;
    }

    public String getPartCategory() {
        return partCategory;
    }

    public void setPartCategory(String partCategory) {
        this.partCategory = partCategory;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }
}

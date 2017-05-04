package com.infoshareacademy.jjdd1.kiomi.app.statistics;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.persistence.*;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by marcin on 22.04.17.
 */

@Entity
@Table(name = "SEARCH_HISTORY")
public class Statistics {
    private static final Logger LOGGER = LogManager.getLogger(Statistics.class);

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDateTime;


    @Column
    private String carBrand;


    private String partCategory;


    private String partBrand;


    private String partSerial;


    private boolean promoted;



    public Statistics() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(Date entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getPartCategory() {
        return partCategory;
    }

    public void setPartCategory(String partCategory) {
        this.partCategory = partCategory;
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

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }


}

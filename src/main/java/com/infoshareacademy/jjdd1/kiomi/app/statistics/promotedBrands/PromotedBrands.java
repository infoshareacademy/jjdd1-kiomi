package com.infoshareacademy.jjdd1.kiomi.app.statistics.promotedBrands;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by marcin on 14.05.17.
 */
@Entity
@Table(name = "promoted_brand")
public class PromotedBrands {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time")
    private Date entryDate;

    @Column(name = "brand", length = 20)
    private String brand;

    public PromotedBrands() {
    }

    public Long getId() {
        return id;
    }

    public PromotedBrands setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public PromotedBrands setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public PromotedBrands setBrand(String brand) {
        this.brand = brand;
        return this;
    }
}

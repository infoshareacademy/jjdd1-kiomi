package com.infoshareacademy.jjdd1.kiomi;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by marcin on 22.04.17.
 */

@Entity
public class Statistics implements Serializable {

    @Id
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(nullable = false)
    private String carBrand;

    @Column
    private String partBrand;

    @Column
    private String partSerial;

    @Column
    private String partCategory;

    @Column(nullable = false)
    private boolean promoted;



}

package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.services.Statistics;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;

/**
 * Created by marcinplonka on 24.04.17.
 */


@Stateless
public class StatisticDataBuilder{


    private String currentPartBrand;
public StatisticDataBuilder() throws IOException {
    }

    Date date = new Date();
    private void setDataChoosenByClient(Statistics statistics) {
        statistics.setId(null);
        statistics.setCreateTime(date);
        statistics.setCarBrand("ljhglqhj");
        statistics.setPartCategory("ljhwgcf;w");
        statistics.setPartBrand("jlhg   efl");
        statistics.setPromoted(false);




    }

//    private boolean promoted = PromotedBrandsLoader.promotedBrandsReader().stream()
//            .map(currentPartBrand).













}

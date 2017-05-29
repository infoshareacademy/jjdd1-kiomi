
package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Part;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PromotedBrandsLoader {
    private static final Logger LOGGER = LogManager.getLogger(PromotedBrandsLoader.class);
    private static List<String> promotedBrandsList = new ArrayList<>();


    static List<Part> rewritedPartList = new ArrayList();


    public List<String> promotedBrandsReader() throws IOException {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("database-autoparts");
        EntityManager entityManager = emf.createEntityManager();
        Query q = entityManager.createQuery("SELECT c.brand FROM PromotedBrands c");
        promotedBrandsList = q.getResultList();

        return promotedBrandsList;
    }

    public List<Part> rewritedPartListSorter(List<Part> originalPartList) throws IOException {


        rewritedPartList.clear();
        promotedBrandsList = promotedBrandsReader();
        if (promotedBrandsList.size() > 0) {
            for (String promotedBrand : promotedBrandsList) {
                for (Part originalPartFromList : originalPartList) {
                    if (promotedBrand.equals(originalPartFromList.getBrand_clear())) {
                        rewritedPartList.add(originalPartFromList);
                    }
                }
            }
            for (String promotedBrand : promotedBrandsList) {
                for (Part originalPartFromList : originalPartList) {
                    if (!promotedBrand.equals(originalPartFromList.getBrand_clear())) {
                        rewritedPartList.add(originalPartFromList);
                    }
                }
            }
            return rewritedPartList;
        }
        return originalPartList;
    }
}
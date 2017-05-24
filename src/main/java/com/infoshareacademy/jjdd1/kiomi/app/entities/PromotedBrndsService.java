package com.infoshareacademy.jjdd1.kiomi.app.entities;

import com.infoshareacademy.jjdd1.kiomi.app.statistics.PromotedBrands;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

/**
 * Created by arek50 on 2017-05-24.
 */
public class PromotedBrndsService {
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(PromotedBrndsService.class);

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.infoshareacademy.jjdd1.kiomi");
    EntityManager entityManager = emf.createEntityManager();

    public List<PromotedBrands> getPromotedBrands() {
        List<PromotedBrands> brandsList = entityManager.createQuery("from PromotedBrands ", PromotedBrands.class)
                .getResultList();
        for (PromotedBrands brand : brandsList) {
            LOGGER.debug("Promoted brands are: " + brand.getBrand());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return brandsList;
    }
    public void setPromotedBrands(List<PromotedBrands> promotedBrandsList) {
        entityManager.getTransaction().begin();
        PromotedBrands promotedBrands = new PromotedBrands();
        for (PromotedBrands brand:promotedBrandsList) {
            promotedBrands.setBrand(brand.getBrand());
        }
        promotedBrands.setEntryDate(new Date());
        entityManager.persist(promotedBrands);
    }
    public void removePromotedBrands(List<PromotedBrands> promotedBrandsList) {
        entityManager.getTransaction().begin();
        entityManager.
                createQuery("DELETE FROM PromotedBrands p WHERE p.brand = :carBrandToRemove").
                setParameter("carBrandToRemove", promotedBrandsList).executeUpdate();
        entityManager.getTransaction().commit();

    }
}

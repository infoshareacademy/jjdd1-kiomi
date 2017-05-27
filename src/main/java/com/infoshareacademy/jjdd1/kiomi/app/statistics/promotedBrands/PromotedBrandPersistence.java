package com.infoshareacademy.jjdd1.kiomi.app.statistics.promotedBrands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by marcin on 27.05.17.
 */
@Stateless
public class PromotedBrandPersistence implements IPromotedBrands {
    private static final Logger LOGGER = LogManager.getLogger(PromotedBrandPersistence.class);
    private List<PromotedBrands> promotedBrandList;

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("database-autoparts");
    private EntityManager entityManager = emf.createEntityManager();



    @Override
//    @Transactional
    public void addBrand(String promotedBrandToAdd) {


        PromotedBrands promotedBrand = new PromotedBrands();
        LOGGER.error("1.1");

        promotedBrand.setBrand(promotedBrandToAdd);
        LOGGER.error("1.2");
        promotedBrand.setEntryDate(new Date());
        LOGGER.error(("1.3"));
        entityManager.getTransaction().begin();
        LOGGER.error("1.4");
        entityManager.persist(promotedBrand);
        LOGGER.error("1.5");
        entityManager.getTransaction().commit();
        LOGGER.error("1.6");

    }

    @Override
//    @Transactional
    public void removeBrand(String promotedBrandToRemove) {

        Query q = entityManager.
                createQuery("DELETE FROM PromotedBrands p WHERE p.brand = :promotedBrandToRemove").
                setParameter("promotedBrandToRemove", promotedBrandToRemove);
        entityManager.getTransaction().begin();
        q.executeUpdate();
        entityManager.getTransaction().commit();


    }

    @Override
//    @Transactional
    public List<PromotedBrands> getAllBrands() {
        TypedQuery<PromotedBrands> typedQuery = entityManager
                .createQuery("SELECT c FROM PromotedBrands c", PromotedBrands.class);
        LOGGER.error("query "+typedQuery);
        typedQuery
                .getResultList()
                .forEach(b -> LOGGER.debug("Promoted brands are: " +b.getBrand()));
        return typedQuery
                .getResultList();



//        promotedBrandList =
//                entityManager.createQuery("SELECT brand FROM PromotedBrands", List<PromotedBrands>.class);
//
//        List promotedBrandList = query.getResultList();
//        promotedBrandList forEach(b -> LOGGER.debug("Promoted brands are: " +b));

    }
}

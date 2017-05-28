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

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("database-autoparts");
    private EntityManager entityManager = emf.createEntityManager();



    @Override
    @Transactional
    public void addBrand(String promotedBrandToAdd) {

        PromotedBrands promotedBrand = new PromotedBrands();

        promotedBrand.setBrand(promotedBrandToAdd);
        promotedBrand.setEntryDate(new Date());
        entityManager.getTransaction().begin();
        entityManager.persist(promotedBrand);
        entityManager.getTransaction().commit();


    }

    @Override
    @Transactional
    public void removeBrand(String promotedBrandToRemove) {



        Query q = entityManager.
                createQuery("DELETE FROM PromotedBrands p WHERE p.brand = :promotedBrandToRemove").
                setParameter("promotedBrandToRemove", promotedBrandToRemove);
        entityManager.getTransaction().begin();
        q.executeUpdate();
        entityManager.getTransaction().commit();




    }

    @Override
    @Transactional
    public List<PromotedBrands> getAllBrands() {


            TypedQuery<PromotedBrands> typedQuery = entityManager
                    .createQuery("SELECT c FROM PromotedBrands c", PromotedBrands.class);
            typedQuery
                    .getResultList()
                    .forEach(b -> LOGGER.debug("Current list of promoted brands: " + b.getBrand()));

        return typedQuery
                    .getResultList();


    }


}

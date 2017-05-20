package com.infoshareacademy.jjdd1.kiomi.app.statistics;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.*;


public class StatisticDataBuilder {
    private static final Logger LOGGER = LogManager.getLogger(StatisticDataBuilder.class);


    public static void addEntryToDatabase(Statistics statistics) {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.infoshareacademy.jjdd1.kiomi");
            EntityManager entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(statistics);
            entityManager.getTransaction().commit();
            entityManager.close();

        LOGGER.debug("Object succefully saved to database. Id nr: "+statistics.getId());
    }
}
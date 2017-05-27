package com.infoshareacademy.jjdd1.kiomi.app.statistics;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import javax.ejb.Stateful;
import javax.persistence.*;

@Stateful
public class StatisticDataBuilder {
    private static final Logger LOGGER = LogManager.getLogger(StatisticDataBuilder.class);


    public void addEntryToDatabase(Statistics statistics) throws HibernateException {

            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("database-autoparts");
            EntityManager entityManager = emf
                    .createEntityManager();
            entityManager
                    .getTransaction()
                    .begin();
            entityManager.persist(statistics);
            entityManager.getTransaction()
                    .commit();
            entityManager
                    .close();

        LOGGER.debug("Object successfully saved in the database. Id: "+statistics.getId());
    }


}
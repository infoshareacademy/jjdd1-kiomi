package com.infoshareacademy.jjdd1.kiomi.app.services;


import com.infoshareacademy.jjdd1.kiomi.app.services.users.UsersList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateful
public class MembersDataBuilder {
    private static final Logger LOGGER = LogManager.getLogger(MembersDataBuilder.class);

    public void addEntryToDatabase(UsersList user) {

        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("database-autoparts");

        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {

        } finally {
            entityManager.close();
            LOGGER.debug("Object successfully saved into the database. Id nr: " + user.getId());
        }


    }

}
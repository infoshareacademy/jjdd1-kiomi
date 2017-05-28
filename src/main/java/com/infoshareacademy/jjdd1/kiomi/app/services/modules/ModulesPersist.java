package com.infoshareacademy.jjdd1.kiomi.app.services.modules;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by marcin on 28.05.17.
 */
public class ModulesPersist implements IModules {



    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("database-autoparts");
    private EntityManager entityManager = emf.createEntityManager();


    @Override
    @Transactional
    public void addModule(Modules moduleToAdd) {

            entityManager.getTransaction().begin();
            entityManager.persist(moduleToAdd);
            entityManager.getTransaction().commit();

    }

        @Override
        @Transactional
        public void updateModule (String name,boolean status) {

            Query q = entityManager.
                    createQuery("UPDATE Modules m SET m.status = :status WHERE m.moduleName = :name")
                    .setParameter("status", status).setParameter("name", name);
            entityManager.getTransaction().begin();
            q.executeUpdate();
        }

        @Override
        @Transactional
        public boolean getStatusOfModule (String name){

            Query q = entityManager.createQuery("SELECT m.status from Modules m WHERE m.moduleName = :name").setParameter("name", name);


            return (boolean) q.getSingleResult();


        }

        @Override
        @Transactional
        public void deleteModule (String name){



                Query q = entityManager.
                        createQuery("DELETE FROM Modules m WHERE m.moduleName = :name").
                        setParameter("name", name);
                entityManager.getTransaction().begin();
                q.executeUpdate();
                entityManager.getTransaction().commit();



        }

        @Override
        @Transactional
        public List<Modules> getAllModules () {

                TypedQuery<Modules> typedQuery = entityManager
                        .createQuery("SELECT m FROM Modules m", Modules.class);

                return typedQuery.getResultList();
        }
    }


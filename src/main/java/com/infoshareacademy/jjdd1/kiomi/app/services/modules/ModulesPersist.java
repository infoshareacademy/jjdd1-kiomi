package com.infoshareacademy.jjdd1.kiomi.app.services.modules;
import javax.persistence.*;
import java.util.List;

/**
 * Created by marcin on 28.05.17.
 */
public class ModulesPersist implements IModules {



    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("database-autoparts");
    private EntityManager entityManager = emf.createEntityManager();


    @Override
    public void addModule(Modules moduleToAdd) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(moduleToAdd);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {

        } finally {
            entityManager.close();

        }
    }

        @Override
        public void updateModule (String name,boolean status){
            try {

                Query q = entityManager.
                        createQuery("UPDATE Modules m SET m.status = :status WHERE m.moduleName = :name")
                        .setParameter("status", status).setParameter("name", name);
                entityManager.getTransaction().begin();
                q.executeUpdate();
                entityManager.getTransaction().commit();

            } catch (Exception ex) {

            } finally {
                entityManager.close();
            }

        }

        @Override
        public boolean getStatusOfModule (String name){

            Query q = entityManager.createQuery("SELECT m.status from Modules m WHERE m.moduleName = :name").setParameter("name", name);

            entityManager.close();
            return (boolean) q.getSingleResult();


        }

        @Override
        public void deleteModule (String name){


            try {

                Query q = entityManager.
                        createQuery("DELETE FROM Modules m WHERE m.moduleName = :name").
                        setParameter("name", name);
                entityManager.getTransaction().begin();
                q.executeUpdate();
                entityManager.getTransaction().commit();

            } catch (Exception ex) {

            } finally {
                entityManager.close();
            }

        }

        @Override
        public List<Modules> getAllModules () {

                TypedQuery<Modules> typedQuery = entityManager
                        .createQuery("SELECT m FROM Modules m", Modules.class);

                return typedQuery.getResultList();
        }
    }


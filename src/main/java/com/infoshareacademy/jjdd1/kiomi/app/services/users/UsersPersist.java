package com.infoshareacademy.jjdd1.kiomi.app.services.users;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marcin on 28.05.17.
 */
public class UsersPersist implements IUsers {


    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("database-autoparts");
    private EntityManager entityManager = emf.createEntityManager();



    @Override
    public void addUser(UsersList userToAdd) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userToAdd);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void removeUser(String emailOfUserToRemove) {

        try {

            Query q = entityManager.
                    createQuery("DELETE FROM UsersList u WHERE u.email = :email").
                    setParameter("email", emailOfUserToRemove);
            entityManager.getTransaction().begin();
            q.executeUpdate();
            entityManager.getTransaction().commit();

        } catch (Exception ex) {

        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<UsersList> getAllUsers() {

        TypedQuery<UsersList> typedQuery = entityManager
                .createQuery("SELECT u FROM UsersList u", UsersList.class);

        return typedQuery.getResultList();
    }

}

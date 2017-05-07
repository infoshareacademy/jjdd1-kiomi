package com.infoshareacademy.jjdd1.kiomi.app.statistics;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class StatisticDataBuilder{
    private static final Logger LOGGER = LogManager.getLogger(StatisticDataBuilder.class);


    public static void buildEntryToDatabase() {
        //experimentally

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("statistics");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Statistics statistics = new Statistics();
        statistics.setEntryDateTime(new Date());
        statistics.setCarBrand("Ford");
        statistics.setPartCategory("Engine");
        statistics.setPartBrand("ATE");
        statistics.setPartSerial("000222555PPP");
        statistics.setPromoted(true);
        entityManager.persist(statistics);
        entityManager.getTransaction().commit();

//        try {
//            factory = new Configuration().
//                    configure().
//                    addAnnotatedClass(Statistics.class).
//                    buildSessionFactory();
//        } catch (Throwable ex) {
//            LOGGER.error("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//

//
//      /* Add few employee records in database */
//        Integer entryId1 = sdb.addEntryToDatabase("Ford", "engine", "Bosh", "2255DDKKLL", false);


//      /* List down all the employees */
//        ME.listEmployees();
//
//      /* Update employee's records */
//        ME.updateEmployee(empID1, 5000);
//
//      /* Delete an employee from the database */
//        ME.deleteEmployee(empID2);
//
//      /* List down new list of the employees */
//        ME.listEmployees();
    }



}


//    /* Method to CREATE an employee in the database */
//    public Integer addEntryToDatabase(String carBrand, String partCategory, String partBrand, String partSerial, boolean promoted) {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        Integer statisticID = null;
//        try {
//            tx = session.beginTransaction();
//            Statistics statistic = new Statistics();
//            statistic.setId(null);
//            statistic.setEntryDateTime(new Date());
//            statistic.setCarBrand(carBrand);
//            statistic.setPartCategory(partCategory);
//            statistic.setPartBrand(partBrand);
//            statistic.setPartSerial(partSerial);
//            statistic.setPromoted(promoted);
//            statisticID = (Integer) session.save(statistic);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return statisticID;
//    }
//
//
//}

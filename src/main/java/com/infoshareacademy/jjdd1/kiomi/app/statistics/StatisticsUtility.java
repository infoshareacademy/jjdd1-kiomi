package com.infoshareacademy.jjdd1.kiomi.app.statistics;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StatisticsUtility {

    public static final String DATABASE_AUTOPARTS = "database-autoparts";
    List<Statistics> listOfAllStatistics = new ArrayList<>();

    public List<Statistics> getAllData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_AUTOPARTS);
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Statistics e");
        return query.getResultList();
    }


//    in this case there is a need to use a native sql query
    public Map<String,BigInteger> getBrandAndCountMap() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_AUTOPARTS);
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createNativeQuery("  SELECT COUNT(car_brand), car_brand FROM statistics.search_history GROUP BY car_brand ORDER BY COUNT(car_brand) DESC;");

        List<Object[]> resultList = query.getResultList();

        Map<String,BigInteger> brandAndCountMap = new LinkedHashMap<>();

        for (Object[] item : resultList) {
            brandAndCountMap.put((String)item[1],(BigInteger) item[0] );
//            System.out.println( (String)item[1]+" "+(BigInteger) item[0]);
        }
        return brandAndCountMap;
    }

    public Map<String,BigInteger> getModelAndCountMap() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_AUTOPARTS);
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createNativeQuery("  SELECT COUNT(car_model), car_model FROM statistics.search_history GROUP BY car_model ORDER BY COUNT(car_model) DESC;");

        List<Object[]> resultList = query.getResultList();

        Map<String,BigInteger> modelAndCountMap = new LinkedHashMap<>();

        for (Object[] item : resultList) {
            modelAndCountMap.put((String)item[1],(BigInteger) item[0] );
//            System.out.println( (String)item[1]+" "+(BigInteger) item[0]);
        }
        return modelAndCountMap;
    }

    public Map<String,BigInteger> getTypeAndCountMap() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_AUTOPARTS);
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createNativeQuery("  SELECT COUNT(car_type), car_type FROM statistics.search_history GROUP BY car_type ORDER BY COUNT(car_type) DESC;");

        List<Object[]> resultList = query.getResultList();

        Map<String,BigInteger> typeAndCountMap = new LinkedHashMap<>();

        for (Object[] item : resultList) {
            typeAndCountMap.put((String)item[1],(BigInteger) item[0] );
//            System.out.println( (String)item[1]+" "+(BigInteger) item[0]);
        }
        return typeAndCountMap;
    }

    public Map<String,BigInteger> getPartBrandAndCountMap() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_AUTOPARTS);
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createNativeQuery("  SELECT COUNT(part_brand), part_brand FROM statistics.search_history GROUP BY part_brand ORDER BY COUNT(part_brand) DESC;");

        List<Object[]> resultList = query.getResultList();

        Map<String,BigInteger> partBrandAndCountMap = new LinkedHashMap<>();

        for (Object[] item : resultList) {
            partBrandAndCountMap.put((String)item[1],(BigInteger) item[0] );
//            System.out.println( (String)item[1]+" "+(BigInteger) item[0]);
        }
        return partBrandAndCountMap;
    }
    public Map<String,BigInteger> getPartCategoryAndCountMap() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_AUTOPARTS);
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createNativeQuery("  SELECT COUNT(part_category), part_category FROM statistics.search_history GROUP BY part_category ORDER BY COUNT(part_category) DESC;");

        List<Object[]> resultList = query.getResultList();

        Map<String,BigInteger> partCategoryAndCountMap = new LinkedHashMap<>();

        for (Object[] item : resultList) {
            partCategoryAndCountMap.put((String)item[1],(BigInteger) item[0] );
//            System.out.println( (String)item[1]+" "+(BigInteger) item[0]);
        }
        return partCategoryAndCountMap;
    }
    public Map<String,BigInteger> getPartNameAndCountMap() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_AUTOPARTS);
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createNativeQuery("  SELECT COUNT(part_name), part_name FROM statistics.search_history GROUP BY part_name ORDER BY COUNT(part_name) DESC;");

        List<Object[]> resultList = query.getResultList();

        Map<String,BigInteger> partNameAndCountMap = new LinkedHashMap<>();

        for (Object[] item : resultList) {
            partNameAndCountMap.put((String)item[1],(BigInteger) item[0] );
//            System.out.println( (String)item[1]+" "+(BigInteger) item[0]);
        }
        return partNameAndCountMap;
    }

}
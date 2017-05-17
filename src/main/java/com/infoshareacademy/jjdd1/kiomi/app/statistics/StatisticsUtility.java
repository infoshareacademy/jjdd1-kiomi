package com.infoshareacademy.jjdd1.kiomi.app.statistics;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsUtility {

    List<Statistics> listOfAllStatistics=new ArrayList<>();

   public List<Statistics>  getAllData(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.infoshareacademy.jjdd1.kiomi");
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Statistics e");
        return query.getResultList();
    }

//    public List<Statistics>  getCarBrandList(){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.infoshareacademy.jjdd1.kiomi");
//        EntityManager entityManager = emf.createEntityManager();
//        Query query = entityManager.createQuery("SELECT c ,COUNT (c.carBrand) FROM Statistics c GROUP BY c.carBrand", Statistics.class);
//
//        List<Object[]> rawResult = query.getResultList();
//
//        Map<String, String> processedResult = new HashMap<>();
//        for (Object[] item : rawResult) {
//            processedResult.put((String)item[0], (String)item[1]);
//            System.out.println((String)item[0] + " " + (String)item[1]);
//        }
//
//        return query.getResultList();
//    }

}

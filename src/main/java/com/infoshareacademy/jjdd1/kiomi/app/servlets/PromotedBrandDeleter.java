package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.statistics.PromotedBrands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by marcin on 14.05.17.0
 */


@WebServlet(urlPatterns = "/delbrand")
public class PromotedBrandDeleter extends HttpServlet{
    Logger LOGGER = LogManager.getLogger(PromotedBrandDeleter.class);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminPromotedbrandForm.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String carBrandToDelete = req.getParameter("carBrandToDelete");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.infoshareacademy.jjdd1.kiomi");
        EntityManager entityManager = emf.createEntityManager();


        String query = "delete from promoted_brand where brand = " + carBrandToDelete;
        LOGGER.debug("Query: " + query);

        String q2 = "DELETE FROM promoted_brand WHERE brand = '" + carBrandToDelete + "'";
        Query q = entityManager.
                createQuery("DELETE FROM PromotedBrands p WHERE p.brand = :carBrandToDelete").
                setParameter("carBrandToDelete", carBrandToDelete);

        entityManager.getTransaction().begin();
        q.executeUpdate();
        entityManager.getTransaction().commit();


        List<PromotedBrands> brandsList = entityManager.createQuery("from PromotedBrands ", PromotedBrands.class)
                .getResultList();
        for (PromotedBrands brand : brandsList) {
            LOGGER.debug("Promoted brands are: " + brand.getBrand());
        }

        entityManager.close();



        req.setAttribute("brandsList", brandsList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminPromotedbrandForm.jsp");
        dispatcher.forward(req,resp);
    }
}

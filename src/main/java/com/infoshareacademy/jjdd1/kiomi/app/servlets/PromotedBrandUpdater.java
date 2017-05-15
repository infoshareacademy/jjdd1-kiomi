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
import java.util.Date;
import java.util.List;

/**
 * Created by marcin on 14.05.17.0
 */


@WebServlet(urlPatterns = "/addbrand")
public class PromotedBrandUpdater extends HttpServlet{
    Logger LOGGER = LogManager.getLogger(PromotedBrandUpdater.class);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminPromotedbrandForm.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String carBrandToAdd = req.getParameter("carBrandToAdd");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.infoshareacademy.jjdd1.kiomi");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
            PromotedBrands promotedBrands = new PromotedBrands();
            promotedBrands.setBrand(carBrandToAdd);
            promotedBrands.setEntryDate(new Date());
            entityManager.persist(promotedBrands);


            List<PromotedBrands> brandsList = entityManager.createQuery("from PromotedBrands ", PromotedBrands.class)
                    .getResultList();
            for (PromotedBrands brand : brandsList) {
                LOGGER.debug("Promoted brands are: " + brand.getBrand());
            }
            entityManager.getTransaction().commit();
            entityManager.close();

            req.setAttribute("brandsList", brandsList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminPromotedbrandForm.jsp");
        dispatcher.forward(req,resp);
    }
}
package com.infoshareacademy.jjdd1.kiomi.app.servlets;
import com.google.common.base.Strings;
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

@WebServlet(urlPatterns = "/managebrand")
public class PromotedBrandManager extends HttpServlet{
    Logger LOGGER = LogManager.getLogger(PromotedBrandManager.class);
    private List<PromotedBrands> promotedBrandsList;
    private boolean promotedBrandToAddIsempty;
    private boolean promotedBrandToRemoveIsempty;

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("database-autoparts");
    EntityManager entityManager = emf.createEntityManager();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/promotedBrandManager.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String promotedBrandToAdd = req.getParameter("promotedBrandToAdd");
        String promotedBrandToRemove = req.getParameter("promotedBrandToRemove");
        promotedBrandToAddIsempty = (Strings.isNullOrEmpty(promotedBrandToAdd));
        promotedBrandToRemoveIsempty = (Strings.isNullOrEmpty(promotedBrandToRemove));



        if (!promotedBrandToAddIsempty) {

                entityManager.getTransaction().begin();
                PromotedBrands promotedBrands = new PromotedBrands();
                promotedBrands.setBrand(promotedBrandToAdd);
                promotedBrands.setEntryDate(new Date());
                entityManager.persist(promotedBrands);
                entityManager.getTransaction().commit();
        }

        if (!promotedBrandToRemoveIsempty) {

                Query q = entityManager.
                        createQuery("DELETE FROM PromotedBrands p WHERE p.brand = :promotedBrandToRemove").
                        setParameter("promotedBrandToRemove", promotedBrandToRemove);

                entityManager.getTransaction().begin();
                q.executeUpdate();
                entityManager.getTransaction().commit();
        }

        if (promotedBrandToRemoveIsempty || promotedBrandToAddIsempty) {

                promotedBrandsList = entityManager.createQuery("from PromotedBrands ", PromotedBrands.class)
                        .getResultList();
                for (PromotedBrands brand : promotedBrandsList) {
                    LOGGER.debug("Promoted brands are: " + brand.getBrand());
                }
            }
                entityManager.clear();

                req.setAttribute("promotedBrandsList", promotedBrandsList);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/promotedBrandManager.jsp");
                dispatcher.forward(req, resp);

            }
        }

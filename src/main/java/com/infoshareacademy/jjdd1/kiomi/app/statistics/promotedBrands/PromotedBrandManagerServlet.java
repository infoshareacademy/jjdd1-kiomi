package com.infoshareacademy.jjdd1.kiomi.app.statistics.promotedBrands;

import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
@WebServlet(urlPatterns = "/managebrand")
public class PromotedBrandManagerServlet extends HttpServlet{
    private static final Logger LOGGER = LogManager.getLogger(PromotedBrandManagerServlet.class);


    PromotedBrandPersistence brandPersist = new PromotedBrandPersistence();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PromotedBrands> promotedBrandsList = brandPersist.getAllBrands();
        req.setAttribute("promotedBrandsList", promotedBrandsList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/promotedBrandManager.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean promotedBrandToAddIsempty;
        boolean promotedBrandToRemoveIsempty;

        String formType = req.getParameter("type");
        String promotedBrandToAdd = req.getParameter("brand");
        String promotedBrandToRemove = req.getParameter("id");
        promotedBrandToAddIsempty = (Strings.isNullOrEmpty(promotedBrandToAdd));
        promotedBrandToRemoveIsempty = (Strings.isNullOrEmpty(promotedBrandToRemove));





        if(formType.equals("add") && !promotedBrandToAddIsempty) {
            brandPersist.addBrand(promotedBrandToAdd);

        }

//        if (!promotedBrandToRemoveIsempty) {
//
//
//            brandPersist.removeBrand(promotedBrandToRemove);
//
//
//        }

        List<PromotedBrands> promotedBrandsList = brandPersist.getAllBrands();
        req.setAttribute("promotedBrandsList", promotedBrandsList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/promotedBrandManager.jsp");
        dispatcher.forward(req, resp);

    }

}

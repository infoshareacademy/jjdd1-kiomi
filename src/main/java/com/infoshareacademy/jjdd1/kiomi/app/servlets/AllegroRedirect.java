package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import com.infoshareacademy.jjdd1.kiomi.app.services.MailSender;
import com.infoshareacademy.jjdd1.kiomi.app.services.SessionData;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.StatisticDataBuilder;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.Statistics;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by marcinplonka on 09.05.17.
 */

@WebServlet(urlPatterns = "/redirect")
public class AllegroRedirect extends HttpServlet{

    @Inject
    SessionData sessionData;

    @Inject
    StatisticDataBuilder statisticDataBuilder;


private static final Logger LOGGER = LogManager.getLogger(AllegroRedirect.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carType = sessionData.getCar().getCarType().getName();

//        String partCategory = "Engine";
        String partBrand = "Bosh";
//        String partName = "Knock sensor";
//        String partSerial = "02554486BBB";
        String partName = req.getParameter("partsame");
        String partSerial = req.getParameter("partserial");
        String partCategory = req.getParameter("partcategory");

        Statistics currentSearch = new Statistics();
        currentSearch.setEntryDate(new Date());
        currentSearch.setCarBrand(brandName);
        currentSearch.setCarModel(modelName);
        currentSearch.setCarType(carType);
        currentSearch.setPartCategory(partCategory);
        currentSearch.setPartBrand(partBrand);
        currentSearch.setPartName(partName);
        currentSearch.setPartSerial(partSerial);

        LOGGER.debug("Creating new entity object: "+currentSearch.toString());

        statisticDataBuilder.addEntryToDatabase(currentSearch);

//        String partBrand = req.getParameter("partsrand");

        LOGGER.debug("Success! New entry in the database: id: "+currentSearch.getId()+", "+currentSearch.getCarBrand()+", "
                +currentSearch.getCarModel());

        resp.sendRedirect("https://allegro.pl/listing?string="
                +currentSearch.getCarBrand()+"%20"+currentSearch.getCarModel()+"%20"+currentSearch.getCarType()+
                        "%20"+currentSearch.getPartName()+"%20"+currentSearch.getPartSerial()+
                "&description=1&order=m&bmatch=base-relevance-floki-5-nga-uni-1-2-0222\" target=\"_blank");
    }
}

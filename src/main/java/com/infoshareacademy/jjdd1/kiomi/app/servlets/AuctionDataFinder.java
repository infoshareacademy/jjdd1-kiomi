package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import allegro.ItemsListType;
import com.infoshareacademy.jjdd1.kiomi.app.model.ebay.EbayItems;
import com.infoshareacademy.jjdd1.kiomi.app.services.AllegroLoader;
import com.infoshareacademy.jjdd1.kiomi.app.services.EbayReader;
import com.infoshareacademy.jjdd1.kiomi.app.services.SessionData;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.StatisticDataBuilder;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.Statistics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * Created by arek50 on 2017-05-21.
 */
@WebServlet(urlPatterns = "/productdetails")
public class AuctionDataFinder extends HttpServlet {
    private boolean promoted;
    private Query isPromoted;
    @Inject
    SessionData sessionData;

    @Inject
    StatisticDataBuilder statisticDataBuilder;


    private static final Logger LOGGER = LogManager.getLogger(AuctionDataFinder.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (sessionData.isLogged() == false) {
            resp.sendRedirect("/googlelogin");
        }

        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carType = sessionData.getCar().getCarType().getName();

        String partName = req.getParameter("partname");
        String partSerial = req.getParameter("partserial");
        String partCategory = req.getParameter("partcategory");
        String partBrand = req.getParameter("partbrand");

        EbayReader ebayReader = new EbayReader();
        String encodeParamToUrl = URLEncoder.encode( partBrand + " " + partSerial, "UTF-8");

        List<EbayItems> ebayList = ebayReader.ebayLoader(encodeParamToUrl);

        AllegroLoader allegroLoader = new AllegroLoader();
        List<ItemsListType> allegroList = allegroLoader.allegroLoader(partBrand + " " + partSerial);

        String allegroLink = "https://allegro.pl/listing?string="
                + encodeParamToUrl + "&description=1&order=m&bmatch=base-relevance-floki-5-nga-uni-1-2-0222";
        req.setAttribute("brand", brandName);
        req.setAttribute("model", modelName);
        req.setAttribute("carType", carType);
        req.setAttribute("partName", partName);
        req.setAttribute("partSerial", partSerial);
        req.setAttribute("partCategory", partCategory);
        req.setAttribute("partBrand", partBrand);
        req.setAttribute("ebayList", ebayList);
        req.setAttribute("allegroList", allegroList);
        req.setAttribute("allegroLink", allegroLink);



        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("database-autoparts");
        EntityManager entityManager = emf.createEntityManager();


        isPromoted = entityManager
                .createQuery("SELECT brand FROM PromotedBrands p WHERE p.brand LIKE :partBrand")
                .setParameter("partBrand", partBrand);
        promoted = isPromoted.getResultList().iterator().hasNext();
        Statistics currentSearch = new Statistics();
        currentSearch.setEntryDate(new Date());
        currentSearch.setCarBrand(brandName);
        currentSearch.setCarModel(modelName);
        currentSearch.setCarType(carType);
        currentSearch.setPartCategory(partCategory);
        currentSearch.setPartBrand(partBrand);
        currentSearch.setPartName(partName);
        currentSearch.setPartSerial(partSerial);
        currentSearch.setPromoted(promoted);


        LOGGER.debug("Creating new entity object: " + currentSearch.toString());

        statisticDataBuilder.addEntryToDatabase(currentSearch);

        LOGGER.debug("Success! New entry in the database: id: " + currentSearch.getId() + ", " + currentSearch.getCarBrand() + ", "
                + currentSearch.getCarModel());


        RequestDispatcher dispatcher = req.getRequestDispatcher("listFromAuctions.jsp");
        dispatcher.forward(req, resp);
    }
}

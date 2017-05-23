package com.infoshareacademy.jjdd1.kiomi.app.servlets;

import allegro.*;
import com.infoshareacademy.jjdd1.kiomi.app.model.ebay.EbayItems;
import com.infoshareacademy.jjdd1.kiomi.app.services.EbayReader;
import com.infoshareacademy.jjdd1.kiomi.app.services.SessionData;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.StatisticDataBuilder;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.Statistics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
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
    @Inject
    SessionData sessionData;

    @Inject
    StatisticDataBuilder statisticDataBuilder;


    private static final Logger LOGGER = LogManager.getLogger(AuctionDataFinder.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String brandName = sessionData.getCar().getBrand().getName();
        String modelName = sessionData.getCar().getModel().getName();
        String carType = sessionData.getCar().getCarType().getName();

        String partName = req.getParameter("partname");
        String partSerial = req.getParameter("partserial");
        String partCategory = req.getParameter("partcategory");

        EbayReader ebayReader = new EbayReader();
        String encodeParamToUrl = URLEncoder.encode(brandName + " " + modelName + " " + partSerial, "UTF-8");
        System.out.println(encodeParamToUrl);
        List<EbayItems> ebayList = ebayReader.ebayLoader(encodeParamToUrl);

        //allegro start
        ServiceService allegroWebApiService = new ServiceService();

        ServicePort allegro = allegroWebApiService.getServicePort();

        DoGetItemsListRequest itemsreq = new DoGetItemsListRequest();
        itemsreq.setCountryId(1);
        itemsreq.setWebapiKey("5c6ad4c4");
        Integer scope = 0, size = 5, offset = 0;
        itemsreq.setResultOffset(offset);
        itemsreq.setResultSize(size);
        itemsreq.setResultScope(scope);
        ArrayOfFilteroptionstype filter = new ArrayOfFilteroptionstype();

        FilterOptionsType allFinder = new FilterOptionsType();
        allFinder.setFilterId("search");
        ArrayOfString finder = new ArrayOfString();
        finder.getItem().add(brandName + " " + modelName);
        allFinder.setFilterValueId(finder);
        filter.getItem().add(allFinder);

        itemsreq.setFilterOptions(filter);

        DoGetItemsListResponse doGetItemsList = allegro.doGetItemsList(itemsreq);
//        for (int i = 0; i < doGetItemsList.getItemsList().getItem().size(); i++) {
//            System.out.println(doGetItemsList.getItemsList().getItem().get(0).getItemTitle());
//        }
        List<ItemsListType> allegroList = doGetItemsList.getItemsList().getItem();
        //allegro end


//        System.out.println(ebayList.get(0).getSellingStatus()[0].getConvertedCurrentPrice()[0].getPriceValue());
        String allegroLink = "https://allegro.pl/listing?string="
                + encodeParamToUrl + "%20" + partSerial +
                "&description=1&order=m&bmatch=base-relevance-floki-5-nga-uni-1-2-0222";
        req.setAttribute("brand", brandName);
        req.setAttribute("model", modelName);
        req.setAttribute("carType", carType);
        req.setAttribute("partName", partName);
        req.setAttribute("partSerial", partSerial);
        req.setAttribute("partCategory", partCategory);
        req.setAttribute("ebayList", ebayList);
        req.setAttribute("allegroList", allegroList);
        req.setAttribute("allegroLink", allegroLink);


        String partBrand = "Bosh";
        Statistics currentSearch = new Statistics();
        currentSearch.setEntryDate(new Date());
        currentSearch.setCarBrand(brandName);
        currentSearch.setCarModel(modelName);
        currentSearch.setCarType(carType);
        currentSearch.setPartCategory(partCategory);
        currentSearch.setPartBrand(partBrand);
        currentSearch.setPartName(partName);
        currentSearch.setPartSerial(partSerial);

        LOGGER.debug("Creating new entity object: " + currentSearch.toString());

        statisticDataBuilder.addEntryToDatabase(currentSearch);

//        String partBrand = req.getParameter("partsrand");

        LOGGER.debug("Success! New entry in the database: id: " + currentSearch.getId() + ", " + currentSearch.getCarBrand() + ", "
                + currentSearch.getCarModel());


        RequestDispatcher dispatcher = req.getRequestDispatcher("listFromAuctions.jsp");
        dispatcher.forward(req, resp);
    }
}

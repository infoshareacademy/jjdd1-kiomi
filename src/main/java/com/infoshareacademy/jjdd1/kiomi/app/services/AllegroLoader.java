package com.infoshareacademy.jjdd1.kiomi.app.services;

import allegro.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by arkadiuszzielazny on 05.04.17.
 */
public class AllegroLoader {
    private static final Logger LOGGER = LogManager.getLogger(AllegroLoader.class);
    public static final String WEBAPIKEY = "5c6ad4c4";
    public static final int COUNTRYCODE = 1;

    private ServiceService allegroWebApiService = new ServiceService();


    public List<ItemsListType> allegroLoader(String keyword) {
try {
    ServicePort allegro = allegroWebApiService.getServicePort();

    DoGetItemsListRequest itemsreq = new DoGetItemsListRequest();
    itemsreq.setCountryId(COUNTRYCODE);
    itemsreq.setWebapiKey(WEBAPIKEY);
    Integer scope = 0, size = 5, offset = 0;
    itemsreq.setResultOffset(offset);
    itemsreq.setResultSize(size);
    itemsreq.setResultScope(scope);
    ArrayOfFilteroptionstype filter = new ArrayOfFilteroptionstype();

    FilterOptionsType allFinder = new FilterOptionsType();
    allFinder.setFilterId("search");
    ArrayOfString finder = new ArrayOfString();
    finder.getItem().add(keyword);
    allFinder.setFilterValueId(finder);
    filter.getItem().add(allFinder);

    itemsreq.setFilterOptions(filter);

    DoGetItemsListResponse doGetItemsList = allegro.doGetItemsList(itemsreq);
    System.out.println(keyword);

    return Optional.ofNullable(doGetItemsList.getItemsList().getItem()).orElse(new ArrayList<ItemsListType>());
} catch (NullPointerException e) {
    System.out.println(keyword);
    return new ArrayList<ItemsListType>();
}
    }
}

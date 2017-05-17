package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.kiomi.app.model.EbayItems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by arek50 on 2017-05-17.
 * https://svcs.ebay.com/services/search/FindingService/v1?SECURITY-APPNAME=Arkadius-kiomi-PRD-5dbc19de5-668c115a
 * &OPERATION-NAME=findItemsByKeywords
 * &SERVICE-VERSION=1.0.0
 * &RESPONSE-DATA-FORMAT=XML
 * &callback=_cb_findItemsByKeywords
 * &REST-PAYLOAD
 * &keywords=iPhone
 * &paginationInput.entriesPerPage=5
 * &GLOBAL-ID=EBAY-PL
 * &siteid=0
 * &itemFilter(0).name=MIN_PRICE
 */
public class EbayReader {
    public final static String EBAY_APP_ID = "Arkadius-kiomi-PRD-5dbc19de5-668c115a";
    private final static String EBAY_FINDING_SERVICE_URI = "https://svcs.ebay.com/services/search/FindingService/v1?"
            + "SECURITY-APPNAME={applicationId}"
            + "&OPERATION-NAME={operation}"
            + "&SERVICE-VERSION={version}"
            + "&RESPONSE-DATA-FORMAT={responsedataformat}"
            + "&CALLBACK={callback}"
            + "&REST-PAYLOAD"
            + "&keywords={keywords}"
            + "&paginationInput.entriesPerPage={maxresults}"
            + "&GLOBAL-ID={globalId}"
            + "&siteid=0";
//            + "&itemFilter(0).name={minpricefilter}";


    private static final String SERVICE_VERSION = "1.0.0";
    private static final String OPERATION_NAME = "findItemsByKeywords";
    private static final String GLOBAL_ID_PL = "EBAY-PL";
    private static final String GLOBAL_ID_EN = "EBAY-US";
    private final static int MAX_ITEMS_RESULTS = 5;
    private final static String RESPONSE_DATA_FORMAT = "JSON";
    private final static String CALLBACK = "_cb_findItemsByKeywords";
    private final static String MINPRICEFILTER = "MIN_PRICE";
    private static final String JSON_DATA_TAG = "searchResult";
    private static final String JSON_DATA_TAG2 = "item";


    private String createUri(String keyword) {

        String uri = EBAY_FINDING_SERVICE_URI;
        uri = uri.replace("{version}", SERVICE_VERSION);
        uri = uri.replace("{operation}", OPERATION_NAME);
        uri = uri.replace("{globalId}", GLOBAL_ID_PL);
        uri = uri.replace("{applicationId}", EBAY_APP_ID);
        uri = uri.replace("{keywords}", keyword);
        uri = uri.replace("{maxresults}", "" + MAX_ITEMS_RESULTS);
        uri = uri.replace("{responsedataformat}", RESPONSE_DATA_FORMAT);
        uri = uri.replace("{callback}", CALLBACK);
//        uri = uri.replace("{minpricefilter}", MINPRICEFILTER);

        return uri;
    }

    public List<EbayItems> ebayLoader(String keyword) throws IOException {
        Gson gson = new Gson();
        System.out.println(keyword);
        System.out.println(createUri(keyword));
        InputStream is = new URL(createUri(keyword)).openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//
//        JsonObject response = gson.fromJson(bufferedReader, JsonObject.class);
//        JsonElement data = response.get(JSON_DATA_TAG);

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
//
//                this.getClass().getResourceAsStream("/" + aztecCode)));


        JsonObject response = gson.fromJson(bufferedReader, JsonObject.class);
        JsonArray findItemsByKeywordsResponse = response.get("findItemsByKeywordsResponse").getAsJsonArray();
//        JsonObject da1=da.getAsJsonObject();
//        JsonElement data = da.getAsJsonObject().get("ack");
        JsonObject dataLevel1=findItemsByKeywordsResponse.get(0).getAsJsonObject();
//        JsonElement data2 = data.get(JSON_DATA_TAG2);
        JsonArray searchResult=dataLevel1.get("searchResult").getAsJsonArray();
        JsonElement data=searchResult.get(0).getAsJsonObject().get("item");
        System.out.println(searchResult.get(0).getAsJsonObject().get("item").toString());

        return gson.fromJson(data, new TypeToken<List<EbayItems>>() {
        }.getType());

    }
}

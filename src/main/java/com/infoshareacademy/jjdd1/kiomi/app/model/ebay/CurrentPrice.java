package com.infoshareacademy.jjdd1.kiomi.app.model.ebay;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arek50 on 2017-05-19.
 */
public class CurrentPrice {
    @SerializedName("@currencyId")
    private String currencyId;
    @SerializedName("__value__")
    private String priceValue;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }
}

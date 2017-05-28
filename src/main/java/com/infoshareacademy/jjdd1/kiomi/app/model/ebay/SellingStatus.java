package com.infoshareacademy.jjdd1.kiomi.app.model.ebay;

/**
 * Created by arek50 on 2017-05-19.
 */
public class SellingStatus {
    private CurrentPrice[] currentPrice;
    private ConvertedCurrentPrice[] convertedCurrentPrice;

    public CurrentPrice[] getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(CurrentPrice[] currentPrice) {
        this.currentPrice = currentPrice;
    }

    public ConvertedCurrentPrice[] getConvertedCurrentPrice() {
        return convertedCurrentPrice;
    }

    public void setConvertedCurrentPrice(ConvertedCurrentPrice[] convertedCurrentPrice) {
        this.convertedCurrentPrice = convertedCurrentPrice;
    }
}

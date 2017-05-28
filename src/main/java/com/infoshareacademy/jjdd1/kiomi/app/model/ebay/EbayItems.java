package com.infoshareacademy.jjdd1.kiomi.app.model.ebay;

import java.util.Arrays;

/**
 * Created by arek50 on 2017-05-17.
 */
public class EbayItems {
    private String[] itemId;
    private String[] title;
    private String[] viewItemURL;
    private String[] paymentMethod;
    private String[] location;
    private String[] country;
    private SellingStatus[] sellingStatus;
    private String[] galleryURL;

    public String[] getGalleryURL() {
        return galleryURL;
    }

    public void setGalleryURL(String[] galleryURL) {
        this.galleryURL = galleryURL;
    }

    public SellingStatus[] getSellingStatus() {
        return sellingStatus;
    }

    public void setSellingStatus(SellingStatus[] sellingStatus) {
        this.sellingStatus = sellingStatus;
    }

    public String[] getItemId() {
        return itemId;
    }

    public void setItemId(String[] itemId) {
        this.itemId = itemId;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getViewItemURL() {
        return viewItemURL;
    }

    public void setViewItemURL(String[] viewItemURL) {
        this.viewItemURL = viewItemURL;
    }

    public String[] getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String[] paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public String[] getCountry() {
        return country;
    }

    public void setCountry(String[] country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "EbayItems{" +
                "itemId=" + Arrays.toString(itemId) +
                ", title=" + Arrays.toString(title) +
                ", viewItemUrl=" + Arrays.toString(viewItemURL) +
                ", paymentMethod=" + Arrays.toString(paymentMethod) +
                ", location=" + Arrays.toString(location) +
                ", country=" + Arrays.toString(country) +
                '}';
    }
}

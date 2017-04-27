package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

import javax.enterprise.context.SessionScoped;

/**
 * Created by arek50 on 2017-04-22.
 */

public class Car {
    private Brand brand;
    private Model model;
    private Type carType;

    public Car() {
    }

    public Car(Brand brand, Model model, Type carType) {
        this.brand = brand;
        this.model = model;
        this.carType = carType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Type getCarType() {
        return carType;
    }

    public void setCarType(Type carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand=" + brand +
                ", model=" + model +
                ", carType=" + carType +
                '}';
    }
}

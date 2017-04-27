package com.infoshareacademy.jjdd1.kiomi.app.model.cars;

import com.google.gson.annotations.SerializedName;
/**
 * example data:
 * "dataname": "FORD",
 * "id": "7pz",
 * "name": "5000-Serie",
 * "end_year": "1993",
 * "end_month": "12",
 * "start_year": "1985",
 * "start_month": "01",
 * "vehicle_group": "commercial",
 * "link": "\/api\/v2\/find\/ey\/7pz"
/**
 * Created by arek50 on 2017-04-27.
 */
public class CarFromAztecJson {
    @SerializedName("D1")
    private String brand;

    @SerializedName("D5")
    private String model;

    @SerializedName("P.1")
    private String engineCapacity;

    @SerializedName("P.2")
    private String enginePower;

    @SerializedName("P.3")
    private String fuelType;

    @SerializedName("Rok_produkcji")
    private String productionYear;

    @SerializedName("ErrorText")//poprawnie rozkodowano dane.
    private String errorText;

    public String getModel() {
        return model;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public String getBrand() {
        return brand;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public String toString() {
        return "CarFromAztecJson{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", enginePower='" + enginePower + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", productionYear='" + productionYear + '\'' +
                '}';
    }
}

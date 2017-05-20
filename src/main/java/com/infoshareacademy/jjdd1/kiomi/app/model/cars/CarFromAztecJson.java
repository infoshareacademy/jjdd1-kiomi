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
 * /**
 * Created by arek50 on 2017-04-27.
 */
public class CarFromAztecJson {

    @SerializedName("D1")//@SerializedName(value = "fullName", alternate = "username")
    private String brand;

    @SerializedName("D5")//Configurator.getBrandSuymbol()
    private String model;

    @SerializedName("P.1")
    private String carCapacity;

    @SerializedName("P.2")
    private String carPower;

    @SerializedName("P.3")
    private String carFuelType;

    @SerializedName("Rok_produkcji")
    private String productionYear;

    @SerializedName("ErrorText")//poprawnie rozkodowano dane.
    private String errorText;
    @SerializedName("Error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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

    public String getCarFuelType() {
        return carFuelType;
    }

    public String getCarCapacity() {
        return carCapacity;
    }

    public String getCarPower() {
        return carPower;
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

    public void setCarCapacity(String carCapacity) {
        this.carCapacity = carCapacity;
    }

    public void setCarFuelType(String carFuelType) {
        this.carFuelType = carFuelType;
    }

    public void setCarPower(String carPower) {
        this.carPower = carPower;
    }

    @Override
    public String toString() {
        return "CarFromAztecJson{" +
                "BRAND='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carCapacity='" + carCapacity + '\'' +
                ", carPower='" + carPower + '\'' +
                ", carFuelType='" + carFuelType + '\'' +
                ", productionYear='" + productionYear + '\'' +
                '}';
    }
}

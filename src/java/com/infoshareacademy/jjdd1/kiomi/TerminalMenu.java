package com.infoshareacademy.jjdd1.kiomi;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.PartCategory;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Type;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.*;

/**
 * Created by arkadiuszzielazny on 31.03.17.
 */
public class TerminalMenu {

    static private String clientsCarBrandId;
    static String clientsCarBrand;
    static private String clientsCarModelId;
    static String clientsCarModel;
    static private String clientsCarTypeId;
    static String clientsCarType;
    static String originalClientsCarBrand;
    static String originalClientsCarModel;
    static String originalClientsCarType;
    static private Set<Brand> listOfCarBrands;
    static private Set<Model> listOfCarModels;
    static private Set<Type> listOfCarTypes;
    static boolean isCarBrandOnOurList;
    static boolean isCarModelOnOurList;
    static boolean isCarTypeOnOurList;
    static String brandOfCarFromDatabase;
    static Scanner scanner;

    public static void main(String[] args) {

        setListOfCarBrands();

        //Strona powitalna aplikacji konsolowej Autoparts KIOMI
        System.out.println("Welcome to 'Autoparts KIOMI'. ");
        System.out.println("To test it, please choose: Brand:'Ford' | Model: 'FOCUS III' | Type: '1.0 EcoBoost'");

        scanner = new Scanner(System.in);

        clientBrandCar();

        setListOfCarModels();

        clientModelCar();

        setListOfCarTypes();

        clientTypeCar();

        resultOfClientsChoice();

        scanner.close();
    }

    static String clientBrandCar() {

        System.out.println("Choose a brand of the car:");

        originalClientsCarBrand = scanner.nextLine();

        clientsCarBrand = originalClientsCarBrand.toLowerCase();

        System.out.print("You have choosen brand of the car: '" + originalClientsCarBrand + "'");

        validationOfClientsCarBrand();

        return clientsCarBrand;
    }

    static String clientModelCar() {

        System.out.println("Choose a model of the car:");

        originalClientsCarModel = scanner.nextLine();

        clientsCarModel = originalClientsCarModel.toLowerCase();

        System.out.print("You have choosen model of the car: '" + originalClientsCarModel + "'");

        validationOfClientsCarModel();

        return clientsCarModel;
    }

    static String clientTypeCar(){
        System.out.println("Choose a type of the car:");

        originalClientsCarType = scanner.nextLine();

        clientsCarType = originalClientsCarType.toLowerCase();

        System.out.print("You have choosen type of the car: '" + originalClientsCarType + "'");

        validationOfClientsCarType();

        return clientsCarType;
    }

    static void validationOfClientsCarBrand() {

        isChoosenCarBrandOnOurList();

        if (isCarBrandOnOurList == false) {
            System.out.println(" | Attention: '" + originalClientsCarBrand + "' is not on our list");
            clientBrandCar();
        }
        else if (isCarBrandOnOurList == true) {
            System.out.println(" | Success: '" + originalClientsCarBrand + "' is on our list");
            System.out.println("--------------------------------------------------");
            System.out.println("Your current choise is: Brand: '" + clientsCarBrand+"'" );
        }
    }

    static void validationOfClientsCarModel() {

        isChoosenCarModelOnOurList();

        if (isCarModelOnOurList == false) {
            System.out.println(" | Attention: '" + originalClientsCarModel + "' is not on our list");
            clientModelCar();
        }
        else if (isCarModelOnOurList == true) {
            System.out.println(" | Success: '" + originalClientsCarModel + "' is on our list");
            System.out.println("--------------------------------------------------");
            System.out.println("Your current choise is: Brand: '" + clientsCarBrand+ "' | Model: '" + clientsCarModel+ "' | ");
        }
    }

    static void validationOfClientsCarType(){
        isChoosenCarTypeOnOurList();

        if (isCarTypeOnOurList == false) {
            System.out.println(" | Attention: '" + originalClientsCarType + "' is not on our list");
            clientModelCar();
        }
        else if (isCarTypeOnOurList == true) {
            System.out.println(" | Success: '" + originalClientsCarType + "' is on our list");
            System.out.println("--------------------------------------------------");
            System.out.println("Your current choise is: Brand: '" + clientsCarBrand+ "' | Model: '" + clientsCarModel+ "' | Type: "+ clientsCarType+ "' | ");
        }
    }

    private static boolean isChoosenCarBrandOnOurList() {
        for (Brand value : listOfCarBrands) {

            if (value.getName_clear().equals(clientsCarBrand)) {
                setClientsCarBrandId(value.getId());
                isCarBrandOnOurList = true;
                break;
            } else {
                isCarBrandOnOurList = false;
            }
        }
        return isCarBrandOnOurList;
    }

    private static boolean isChoosenCarModelOnOurList() {
        for (Model value : listOfCarModels) {
            if (value.getName().toLowerCase().equals(clientsCarModel)) {
                setClientsCarModelId(value.getId());
                isCarModelOnOurList = true;
                break;
            } else {
                isCarModelOnOurList = false;
            }
        }
        return isCarModelOnOurList;
    }

    private static boolean isChoosenCarTypeOnOurList() {
        for (Type value : listOfCarTypes) {
            if (value.getName().toLowerCase().equals(clientsCarType)) {
                setClientsCarTypeId(value.getId());
                isCarTypeOnOurList = true;
                break;
            } else {
                isCarTypeOnOurList = false;
            }
        }
        return isCarTypeOnOurList;
    }

    private static void resultOfClientsChoice() {

        for(Model value: CarsDataLoader.loadDataBrandFile(clientsCarBrand)){
            if(value.getName().toLowerCase().equals(clientsCarModel)){
                System.out.println("Brand ID: "+ getClientsCarBrandId());
                System.out.println("Brand Name: "+ clientsCarBrand);

                System.out.println("Model ID: "+ value.getId());
                System.out.println("Model Name: "+ value.getName());
                System.out.println("Model Start Month: "+ value.getStart_month());
                System.out.println("Model Start Year: "+ value.getStart_year());
                System.out.println("Model End Month: "+ value.getEnd_month());
                System.out.println("Model End Year: "+ value.getEnd_year());
                System.out.println("Model Vehicle Group: "+ value.getVehicle_group());
                System.out.println("Model Link: "+ value.getLink());

                for( Type type: listOfCarTypes){
                    if(type.getId().equals(getClientsCarTypeId())){
                        System.out.println("Type ID: "+ getClientsCarTypeId());
                        System.out.println("Type Name: "+ type.getName());
                        System.out.println("Type Start Month: "+ type.getStart_month());
                        System.out.println("Type Start Year: "+ type.getStart_year());
                        System.out.println("Type End Month: "+ type.getEnd_month());
                        System.out.println("Type End Year: "+ type.getEnd_year());
                        System.out.println("Type Ccm: "+ type.getCcm());

                        System.out.println("Type Kw: "+ type.getKw());
                        System.out.println("Type Hp: "+ type.getHp());
                        System.out.println("Type Cylinders: "+ type.getCylinders());
                        System.out.println("Type Engine: "+ type.getEngine());
                        System.out.println("Type Engine txt: "+ type.getEngine_txt());
                        System.out.println("Type Fuel: "+ type.getFuel());
                        System.out.println("Type Body: "+ type.getBody());
                        System.out.println("Type Axle: "+ type.getAxle());
                        System.out.println("Type Max weight: "+ type.getMax_weight());
                        System.out.println("Type Link: "+ type.getLink());
                    }
                }
            }
        }
    }

    static void setListOfCarBrands() {
        listOfCarBrands=new HashSet();
        listOfCarBrands=CarsDataLoader.getListOfCarBrands();
    }

    public static void setListOfCarModels() {
        listOfCarModels = new HashSet();
        // for now the list contains only brand-"Ford" | models "models of Ford cars"
        listOfCarModels = CarsDataLoader.getListOfCarModels();
    }

    public static void setListOfCarTypes() {
        listOfCarTypes = new HashSet();
        listOfCarTypes = CarsDataLoader.getListOfCarTypes();
    }

    public static String getClientsCarBrandId() {
        return clientsCarBrandId;
    }

    public static void setClientsCarBrandId(String clientsCarBrandId) {
        TerminalMenu.clientsCarBrandId = clientsCarBrandId;
    }

    public static String getClientsCarModelId() {
        return clientsCarModelId;
    }

    public static void setClientsCarModelId(String clientsCarModelId) {
        TerminalMenu.clientsCarModelId = clientsCarModelId;
    }

    public static String getClientsCarTypeId() {
        return clientsCarTypeId;
    }

    public static void setClientsCarTypeId(String clientsCarTypeId) {
        TerminalMenu.clientsCarTypeId = clientsCarTypeId;
    }
}

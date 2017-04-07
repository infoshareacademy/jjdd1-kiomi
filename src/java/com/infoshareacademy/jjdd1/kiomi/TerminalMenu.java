package com.infoshareacademy.jjdd1.kiomi;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.PartCategory;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Type;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by arkadiuszzielazny on 31.03.17.
 */
public class TerminalMenu {

    static String clientsCarBrand;
    static String clientsCarModel;
    static String originalClientsCarBrand;
    static String originalClientsCarModel;
    static private List<String> listOfCarBrands;
    static private List<String> listOfCarModels;
    static boolean isCarBrandOnOurList;
    static boolean isCarModelOnOurList;
    static String brandOfCarFromDatabase;
    static Scanner scanner;

    public static void main(String[] args) {

        setListOfCarBrands();

        //Strona powitalna aplikacji konsolowej Autoparts KIOMI
        System.out.println("Welcome to 'Autoparts KIOMI'. ");

        //load Data
        List<Model> x= CarsDataLoader.loadDataBrandFile("FORD".toLowerCase());
        System.out.println(x);
//        System.out.println(x);
        //pobieram index z listy i z niego ID
        String modelID=x.get(1).getId();
        System.out.println(modelID);
        //wybieram model
        List<Type> y= CarsDataLoader.loadDataTypeFile("72o");
        System.out.println(y);
        String typeID=y.get(1).getId();
        System.out.println(typeID);
        //wybieram typ
//        List<PartCategory> z= CarsDataLoader.loadDataPartCategoryFile("2b91");
//        System.out.println(z);
        scanner = new Scanner(System.in);

        clientBrandCar();

        setListOfCarModels();

        clientModelCar();

        scanner.close();
    }

    static String clientBrandCar() {

        System.out.println("Choose a brand of the car from the list:");

        for (String value : listOfCarBrands) {
            System.out.println(" - " + value);
        }

        originalClientsCarBrand = scanner.next();

        clientsCarBrand = originalClientsCarBrand.toLowerCase();

        System.out.print("You have choosen brand of the car: '" + originalClientsCarBrand + "'");

        validationOfClientsCarBrand();

        return clientsCarBrand;
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

    private static boolean isChoosenCarBrandOnOurList() {
        for (String value : listOfCarBrands) {

            if (value.equals(clientsCarBrand)) {
                isCarBrandOnOurList = true;
                break;
            } else {
                isCarBrandOnOurList = false;
            }
        }

        return isCarBrandOnOurList;
    }

    private static boolean isChoosenCarModelOnOurList() {
        for (String value : listOfCarModels) {

            if (value.equals(clientsCarModel)) {
                isCarModelOnOurList = true;
                break;
            } else {
                isCarModelOnOurList = false;
            }
        }
        return isCarModelOnOurList;
    }

    static String clientModelCar() {

        System.out.println("Choose a model of the car from the list:");

        for (String value : listOfCarModels) {
            System.out.println(" - " + value);
        }

        originalClientsCarModel = scanner.next();

        clientsCarModel = originalClientsCarModel.toLowerCase();

        System.out.print("You have choosen model of the car: '" + originalClientsCarModel + "'");

        validationOfClientsCarModel();

        return clientsCarModel;
    }


    static void setListOfCarBrands() {
        listOfCarBrands = new ArrayList<String>();
        listOfCarBrands.add("ford");
        listOfCarBrands.add("kia");
    }

    public static void setListOfCarModels() {
        listOfCarModels = new ArrayList<String>();

        if( clientsCarBrand.equals("ford")){

            listOfCarModels.add("BANTAM");
            listOfCarModels.add("CONSUL");
            listOfCarModels.add("CORTINA");
            listOfCarModels.add("focus");
            listOfCarModels.add("GRANADA");
            listOfCarModels.add("SCORPIO");
        }

        else if(clientsCarBrand.equals("kia")){
            listOfCarModels.add("AMANTI (GH)");
            listOfCarModels.add("AVELLA");
            listOfCarModels.add("BESTA Autobus");
            listOfCarModels.add("JOICE");
            listOfCarModels.add("MAGENTIS");
            listOfCarModels.add("PICANTO");
            listOfCarModels.add("rio");
        }
    }

}

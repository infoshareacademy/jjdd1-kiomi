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

    static String clientsCarBrand;
    static String clientsCarModel;
    static String originalClientsCarBrand;
    static String originalClientsCarModel;
    static private Set<Brand> listOfCarBrands;
    static private Set<Model> listOfCarModels;
    static boolean isCarBrandOnOurList;
    static boolean isCarModelOnOurList;
    static String brandOfCarFromDatabase;
    static Scanner scanner;

    public static void main(String[] args) {

        setListOfCarBrands();

        //Strona powitalna aplikacji konsolowej Autoparts KIOMI
        System.out.println("Welcome to 'Autoparts KIOMI'. ");
        System.out.println("To test it, please choose: Brand:'Ford' | Model: '6000-Serie'");

        //load Data
//        List<Model> x= CarsDataLoader.loadDataBrandFile("FORD".toLowerCase());
//        System.out.println(x);

        //pobieram index z listy i z niego ID
//        String modelID=x.get(1).getId();
//        System.out.println(modelID);
        //wybieram model
//        List<Type> y= CarsDataLoader.loadDataTypeFile("72o");
//        System.out.println(y);
//        String typeID=y.get(1).getId();
//        System.out.println(typeID);
        //wybieram typ
//        List<PartCategory> z= CarsDataLoader.loadDataPartCategoryFile("2b91");
//        System.out.println(z);
        scanner = new Scanner(System.in);

        clientBrandCar();

        setListOfCarModels();

        clientModelCar();

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
        for (Brand value : listOfCarBrands) {

            if (value.getName_clear().equals(clientsCarBrand)) {
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
                isCarModelOnOurList = true;
                break;
            } else {
                isCarModelOnOurList = false;
            }
        }
        return isCarModelOnOurList;
    }

    private static void resultOfClientsChoice() {

        for(Model value: CarsDataLoader.loadDataBrandFile(clientsCarBrand)){
            if(value.getName().toLowerCase().equals(clientsCarModel)){
                System.out.println("ID: "+ value.getId());
                System.out.println("Model: "+ value.getName());
                System.out.println("Start month: "+ value.getStart_month());
                System.out.println("Start year: "+ value.getStart_year());
                System.out.println("End month: "+ value.getEnd_month());
                System.out.println("End year: "+ value.getEnd_year());
                System.out.println("Vehicle group: "+ value.getVehicle_group());
                System.out.println("Link: "+ value.getLink());
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

}

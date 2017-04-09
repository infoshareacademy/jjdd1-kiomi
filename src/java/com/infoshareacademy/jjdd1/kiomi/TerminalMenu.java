package com.infoshareacademy.jjdd1.kiomi;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.String.join;

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

private static Set<Brand> brands = CarsDataLoader.getBrandsList();
    public static void main(String[] args) {

        setListOfCarBrands();

        //Strona powitalna aplikacji konsolowej Autoparts KIOMI
        System.out.println("Welcome to 'Autoparts KIOMI'. ");

        //pobranie lity brandów jako Set
//        System.out.println("Lista brandów:");
        Set<Brand> brands = CarsDataLoader.getBrandsList();
//        System.out.println(brands);
        System.out.println("Wybrano: FORD");
        //pobranie id forda
        String selectedId = "";
        for (Brand brand : brands) {
            if (brand.getName_clear().equals(("FORD".toLowerCase()).replace(" ", ""))) {
                selectedId = brand.getId();
                System.out.println(selectedId);
            }
        }
        if (selectedId.length() == 0) {
            System.out.println("Błedny wpis w wyszukiwarce");
            selectedId = "";
        }
        //pobranie id forda END

        //pobranie listy modeli jako Set
//        System.out.println("Lista modeli:");
        Set<Model> selectedModels = CarsDataLoader.getModelsListById("ey");
//        System.out.println(selectedModels);
        System.out.println("Wybrano: FOCUS III");

        //pobranie listy typów jako Set
//        System.out.println("Lista typów:");
        Set<Type> selectedTypes = CarsDataLoader.getTypesListById("72o");
//        System.out.println(selectedTypes);
        System.out.println("Wybrano: 1.5 TDCi-2b91");

        //pobranie listy kategorii jako Set
//        System.out.println("Lista kategorii:");
        Set<PartCategory> selectedPartCategory = CarsDataLoader.getPartCategoryListById("2b91");
//        System.out.println(selectedPartCategory );
        System.out.println("Wybrano: Układ hamulcowy -7t3");

        //        System.out.println("Lista kategorii:");
        selectedPartCategory = CarsDataLoader.getPartCategoryListById("7t3");
//        System.out.println(selectedPartCategory );
        System.out.println("Wybrano: (8ab) Hamulce tarczowe");
//przed wysłaniem każdej kategorii sprawdzam, czy ma dzieci. Jesli 8ab nie ma dzieci możesz pobrać dzieci lub pobierać produkty

        //        System.out.println("Lista produktów:");
        Set<Part> selectedPart = CarsDataLoader.getPartListById("8ab");
//        System.out.println(selectedPart);
        System.out.println("KONIEC");



        if (selectedPart.size() > 0) {
            System.out.println("Lista produktów:");
            for (Part data : selectedPart) {
                System.out.println("- " + data);
            }

        } else if (selectedPartCategory.size() > 0) {
            System.out.println("Lista kategorii:");
            for (PartCategory data : selectedPartCategory) {
                System.out.println("- " + data);
            }

        } else if (selectedTypes.size() > 0) {
            System.out.println("Lista typów:");
            for (Type data : selectedTypes) {
                System.out.println("- " + data);
            }
        } else if (selectedModels.size() > 0) {
            System.out.println("Lista modeli:");
            for (Model data : selectedModels) {
                System.out.println("- " + data);
            }
        } else if (brands.size() > 0) {
            System.out.println("Lista brandów:");
            for (Brand data : brands) {
                System.out.println("- " + data);
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        scanner = new Scanner(System.in);

        clientBrandCar();

//        setListOfCarModels();
//
//        clientModelCar();

        scanner.close();

    }

    static String clientBrandCar() {

        System.out.println("Choose a brand of the car from the list below:");
//        for (String value : listOfCarBrands) {
//            System.out.println(" - " + value);
//        }

        for (Brand brand : brands) {
            System.out.println("- "+brand);
        }

        originalClientsCarBrand = scanner.next();

        clientsCarBrand = (originalClientsCarBrand.toLowerCase()).replace(" ", "");

        System.out.print("You have choosen brand of the car: '" + originalClientsCarBrand + "'");

        validationOfClientsCarBrand();

        return clientsCarBrand;
    }

    static void validationOfClientsCarBrand() {

        isChoosenCarBrandOnOurList();

        if (isCarBrandOnOurList == false) {
            System.out.println(" | Attention: '" + originalClientsCarBrand + "' is not on our list");
            clientBrandCar();
        } else if (isCarBrandOnOurList == true) {
            System.out.println(" | Success: '" + originalClientsCarBrand + "' is on our list");
            System.out.println("--------------------------------------------------");
            System.out.println("Your current choise is: Brand: '" + clientsCarBrand + "'");
        }
    }

    static void validationOfClientsCarModel() {

        isChoosenCarModelOnOurList();

        if (isCarModelOnOurList == false) {
            System.out.println(" | Attention: '" + originalClientsCarModel + "' is not on our list");
            clientModelCar();
        } else if (isCarModelOnOurList == true) {
            System.out.println(" | Success: '" + originalClientsCarModel + "' is on our list");
            System.out.println("--------------------------------------------------");
            System.out.println("Your current choise is: Brand: '" + clientsCarBrand + "' | Model: '" + clientsCarModel + "' | ");
        }
    }

    private static boolean isChoosenCarBrandOnOurList() {
        for (Brand value : brands) {

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

        if (clientsCarBrand.equals("ford")) {

            listOfCarModels.add("BANTAM");
            listOfCarModels.add("CONSUL");
            listOfCarModels.add("CORTINA");
            listOfCarModels.add("focus");
            listOfCarModels.add("GRANADA");
            listOfCarModels.add("SCORPIO");
        } else if (clientsCarBrand.equals("kia")) {
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

package com.infoshareacademy.jjdd1.kiomi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by arkadiuszzielazny on 31.03.17.
 */
public class TerminalMenu {

    static String clientsCarBrand;
    static String originalClientsCarBrand;
    static private List<String> listOfCarBrands;
    static boolean isCarBrandOnOurList;
    static String brandOfCarFromDatabase;

    public static void main(String[] args) {

        setListOfCarBrands();

        //Strona powitalna aplikacji konsolowej Autoparts KIOMI
        System.out.print("Welcome to 'Autoparts KIOMI'. ");

        clientBrandCar();

    }

    static String clientBrandCar() {

        System.out.println("Choose a brand of the car from the list:");

        for (String value : listOfCarBrands) {
            System.out.println(" - " + value);
        }

        Scanner scanner = new Scanner(System.in);
        originalClientsCarBrand = scanner.next();

        clientsCarBrand = originalClientsCarBrand.toLowerCase();

        System.out.print("You have choosen brand of the car: '" + originalClientsCarBrand + "'");

        validationOfClientsCarBrand();

        scanner.close();

        return clientsCarBrand;
    }

    static void validationOfClientsCarBrand() {

        isChoosenCarModelOnOurList();

        if (isCarBrandOnOurList == false) {
            System.out.println(" | Attention: '" + originalClientsCarBrand + "' is not on our list");
            clientBrandCar();
        }
        else if (isCarBrandOnOurList == true) {
            System.out.println(" | Success: '" + originalClientsCarBrand + "' is on our list");
            System.out.println("--------------------------------------------------");
            System.out.println("Your current choise is: Brand: '" + clientsCarBrand + "' | Model: '" + "' | ");
        }
    }

    private static boolean isChoosenCarModelOnOurList() {
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

    static void setListOfCarBrands() {
        listOfCarBrands = new ArrayList<String>();
        listOfCarBrands.add("ford");
        listOfCarBrands.add("kia");
    }
}

package com.infoshareacademy.jjdd1.kiomi;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;

import java.lang.reflect.Array;
import java.util.*;

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

    private static List<Brand> searchResult = new ArrayList<Brand>();
    private static List<Brand> brands = CarsDataLoader.getBrandsList();
    private static List<Model> models = CarsDataLoader.getModelsList();
    private static List<Type> carTypes = CarsDataLoader.getCarTypesList();
    private static List<PartCategory> partCategory = CarsDataLoader.getPartCategoryList();
    private static List<Part> part = CarsDataLoader.getPartList();

    public static void main(String[] args) {

//        setListOfCarBrands();

        //Strona powitalna aplikacji konsolowej Autoparts KIOMI
        System.out.println("Welcome to 'Autoparts KIOMI'. ");
/*
        //pobranie lity brandów jako Set
//        System.out.println("Lista brandów:");
        List<Brand> brands = CarsDataLoader.getBrandsList();
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
        List<Model> selectedModels = CarsDataLoader.getModelsListById("ey");
//        System.out.println(selectedModels);
        System.out.println("Wybrano: FOCUS III");

        //pobranie listy typów jako Set
//        System.out.println("Lista typów:");
        List<Type> selectedTypes = CarsDataLoader.getTypesListById("72o");
//        System.out.println(selectedTypes);
        System.out.println("Wybrano: 1.5 TDCi-2b91");

        //pobranie listy kategorii jako Set
//        System.out.println("Lista kategorii:");
        List<PartCategory> selectedPartCategory = CarsDataLoader.getPartCategoryListById("2b91");
//        System.out.println(selectedPartCategory );
        System.out.println("Wybrano: Układ hamulcowy -7t3");

        //        System.out.println("Lista kategorii:");
        selectedPartCategory = CarsDataLoader.getPartCategoryListById("7t3");
//        System.out.println(selectedPartCategory );
        System.out.println("Wybrano: (8ab) Hamulce tarczowe");
//przed wysłaniem każdej kategorii sprawdzam, czy ma dzieci. Jesli 8ab nie ma dzieci możesz pobrać dzieci lub pobierać produkty

        //        System.out.println("Lista produktów:");
        List<Part> selectedPart = CarsDataLoader.getPartListById("8ab");
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
*/

//        scanner = new Scanner(System.in);

//        clientBrandCar();

//        setListOfCarModels();
//
//        clientModelCar();

//        scanner.close();


        mainMenu();
    }

    private static String[] QuestionsToTheMenu = {"Podaj markę:", "Podaj model:", "Podaj typ silnika:", "Podaj kategorię:", "Zamówienie złożysz telefonicznie pod numerem 55555555 podając kod wewnętrzny(KOD WEW)\nWybierz części podając ich numery z wyszukiwarki.\nWybierz część:"};
    private static String[] ListDataToTheMenu = {"Lista maerek:", "Lista modeli:", "Lista typów silnika:", "Lista kategorii:", "Lista części"};
    private static List<?>[] objToMenu = {brands, models, carTypes, partCategory, part};
    private static List<String> searchResultInfo = new ArrayList();
    private static int lastSearchedId;
    private static String partCategoryId = "";

    public static void mainMenu() {
        System.out.println("Witaj w hurtownii części samochodowych");
        System.out.println("---------------------------------------");
        scannerData();
    }

    public static void scannerData() {
        int i = 0;
        while (i <= 5) {

            System.out.println(ListDataToTheMenu[i]);
            listOfDataType(i);
            System.out.println("Jeśli lista jest zadługa wpisz pierwszą literę wyszukiwanej frazy".toUpperCase());
            if (i == 3) {
                System.out.println("---------------------------------------");
                System.out.println("SUBMENU:");
                System.out.println("[0-9]:: Podaj numer kategorii");
                System.out.println("CZESC:: Podaj 'C'(części), aby wyświetlić listę części");
                System.out.println("GORA:: Podaj 'G'(góra), aby wyjść do wyższego poziomu menu");
                System.out.println("MODEL:: Podaj 'M'(model), aby wyświetlić info o modelu");
                System.out.println("SILNIK:: Podaj 'S'(silnik), aby wyświetlić info o silniku");
                System.out.println("KONIEC:: Podaj 'K'(koniec), aby wyjść z programu");
            }
            if (i > 0) {
                System.out.println("---------------------------------------");
                System.out.println("Do tej pory wybrałeś: ");
                getSearchingInfo();
                System.out.println("---------------------------------------");
            }
            i = scannerForWhile(i);

        }
        scanner.close();
    }

    private static int scannerForWhile(int i) {
        System.out.print(QuestionsToTheMenu[i]);
        scanner = new Scanner(System.in);
        String insertedData = scanner.nextLine();

        if (i > 2 && (insertedData.toLowerCase()).replace(" ", "").equals("p")) {
            return 4;
        }
        if (validateData(insertedData, i) && i < 3) {
            i++;
        }

        return i;
    }

    public static void listOfDataType(int element) {
        switch (element) {
            case 0:
                brands = CarsDataLoader.getBrandsList();
                printListOfDataType(brands);
                break;
            case 1:
                String brandId = brands.get(lastSearchedId).getId();

                models = CarsDataLoader.getModelsListById(brandId);
                printListOfDataType(models);
                break;
            case 2:
                String modelId = models.get(lastSearchedId).getId();
                carTypes = CarsDataLoader.getTypesListById(modelId);
                printListOfDataType(carTypes);
                break;
            case 3:
                String carTypeId;

                if (partCategoryId == "") {
                    carTypeId = carTypes.get(lastSearchedId).getId();
                    partCategoryId = "-";
                } else {
                    carTypeId = partCategory.get(lastSearchedId).getId();
                }
                partCategoryId = carTypeId;
                partCategory = CarsDataLoader.getPartCategoryListById(carTypeId);
               printListOfDataType(partCategory);
                break;
            case 4:

//                partCategoryId = partCategory.get(lastSearchedId).getId();
                part = CarsDataLoader.getPartListById(partCategoryId);
                printListOfDataType(part);
                break;
        }

    }

    public static void printListOfDataType(List c) {
        int i = 1;
        for (Object element : c) {
            System.out.println(i + " - " + element);
            i++;
        }
    }

    public static boolean validateData(String data, int element) {

        int clearData = Integer.parseInt((data.toLowerCase()).replace(" ", "")) - 1;

        if (clearData <= objToMenu[element].size()) {

            lastSearchedId = clearData;
            setSearchingInfo(objToMenu[element].get(clearData).toString());

        } else {
            System.out.println("Nie ma takiego elementu w bazie.");
            scannerForWhile(element);
        }
        return true;
    }


    public static void setSearchingInfo(String element) {
        searchResultInfo.add(element);
    }

    public static void getSearchingInfo() {
        for (String name : searchResultInfo) {
            System.out.println("- " + name);
        }

    }

    //-------------------
    static String clientBrandCar() {

        System.out.println("Choose a brand of the car from the list below:");
//        for (String value : listOfCarBrands) {
//            System.out.println(" - " + value);
//        }

        for (Brand brand : brands) {
            System.out.println("- " + brand);
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
            System.out.println("Your current choise is: Brand: '" + searchResult.get(searchResult.size() - 1).getName() + "'");
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
            System.out.println("Your current choise is: Brand: '" + searchResult.get(searchResult.size() - 2).getName() + "' | Model: '" + searchResult.get(searchResult.size() - 1).getName() + "' | ");
        }
    }

    private static boolean isChoosenCarBrandOnOurList() {
        for (Brand value : brands) {

            if (value.getName_clear().equals(clientsCarBrand)) {
                searchResult.add(value);
                isCarBrandOnOurList = true;
                break;
            } else {
                isCarBrandOnOurList = false;
            }
        }

        return isCarBrandOnOurList;
    }

    private static boolean isChoosenCarModelOnOurList() {
        for (Model value : models) {

            if (value.getName().equals(clientsCarModel)) {
//                searchResult.add(value);
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


}

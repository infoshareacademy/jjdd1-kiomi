package com.infoshareacademy.jjdd1.kiomi;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;

import java.util.*;


/**
 * Created by arkadiuszzielazny on 31.03.17.
 */
public class TerminalMenu {


    static Scanner scanner;
    private static CarsDataLoader carsDataLoader = new CarsDataLoader();
    private static List<Brand> brands = new ArrayList();
    private static List<Model> models = new ArrayList();
    private static List<Type> carTypes = new ArrayList();
    private static List<PartCategory> partCategory = new ArrayList();
    private static List<Part> part = new ArrayList();
    private static List<?> referenceForTypeLists;

    public static void main(String[] args) {
        startMenu();
    }

    private static String[] titleForListByData = {"Lista maerek:", "Lista modeli:", "Lista typów silnika:", "Lista kategorii:", "Lista części:"};
    private static String[] QuestionsToTheMenu = {"Podaj markę:", "Podaj model:", "Podaj typ silnika:", "Podaj kategorię:", "Zamówienie złożysz telefonicznie pod numerem 55555555 podając kod wewnętrzny(KOD WEW)\nWybierz części podając ich numery z wyszukiwarki.\nWybierz część:"};
    //    private static List<?>[] objToMenu = {brands, models, carTypes, partCategory, part};
    private static List<String> searchResults = new ArrayList();
    private static int lastSearchedNumberOnTheList;
    private static String partCategoryId = "";
    private static String lastRequestFromUser = "";

    //powitanie
    public static void startMenu() {
        System.out.println("Witaj w hurtownii części samochodowych");
        System.out.println("---------------------------------------");
        printListByData();
    }

    //druk listy
    public static void printListByData() {

        int i = 0;
        while (i <= 5) {
            System.out.println(titleForListByData[i]);
//lista
            listByDataType();
            printListByDataType(referenceForTypeLists);
            System.out.println("Jeśli lista jest za długa wpisz pierwszą literę wyszukiwanej frazy".toUpperCase());

            //submenu
            submenuForStartMenu();
            //pobierz info od usera
            i = requestFromUser(i);

        }
        scanner.close();
    }

    public static void listByDataType() {
        if (brands.size() == 0) {
            brands = carsDataLoader.getBrandsList();
//            printListByDataType(brands);
            referenceForTypeLists = brands;
        } else if (models.size() == 0) {
            String brandId = brands.get(lastSearchedNumberOnTheList).getId();
            models = carsDataLoader.getModelsListById(brandId);
//            printListByDataType(models);
            referenceForTypeLists = models;
        } else if (carTypes.size() == 0) {
            String modelId = models.get(lastSearchedNumberOnTheList).getId();
            carTypes = carsDataLoader.getTypesListById(modelId);
//            printListByDataType(carTypes);
            referenceForTypeLists = carTypes;
        } else if (partCategory.size() == 0) {
            String carTypeId;
            if (partCategoryId == "") {
                carTypeId = carTypes.get(lastSearchedNumberOnTheList).getId();
                partCategoryId = "-";
            } else {
                carTypeId = partCategory.get(lastSearchedNumberOnTheList).getId();
            }
            partCategoryId = carTypeId;
            System.out.println(carTypeId);
            partCategory = carsDataLoader.getPartCategoryListById(carTypeId);
//            printListByDataType(partCategory);
            referenceForTypeLists = partCategory;
            part = carsDataLoader.getPartListById(partCategoryId);
            printListByDataType(part);
        }


    }

    public static void printListByDataType(List c) {
        int i = 1;
        if (lastRequestFromUser.matches("[a-z]")) {
            //print tylko litery
        } else {
            for (Object element : c) {

                System.out.println(i + " - " + element);
                i++;
            }
        }
    }

    public static void submenuForStartMenu() {
        System.out.println("---------------------------------------");
        System.out.println("SUBMENU:");
        System.out.println("[0-9]  :: Wpisz numer kategorii, aby ją wybrać");
        if (searchResults.size() > 0) {
            System.out.println("CZESC  :: Wpisz 'CZESC', aby wyświetlić listę części dla danego etapu wyszukiwania");
            System.out.println("GORA   :: Wpisz 'GORA', aby wyjść do wyższego poziomu menu");
            System.out.println("MODEL  :: Wpisz 'MODEL', aby wyświetlić info o modelu");
            System.out.println("SILNIK :: Wpisz 'SILNIK', aby wyświetlić info o silniku");
            System.out.println("RESET  :: Wpisz 'RESET', aby wyszukiwać od nowa");
            System.out.println("KONIEC :: Wpisz 'KONIEC', aby wyjść z programu");
            reportForMenu();
        }

    }

    public static void reportForMenu() {
        System.out.println("---------------------------------------");
        System.out.println("Do tej pory wybrałeś: ");
        getSearchResults();
        System.out.println("---------------------------------------");
    }


    private static int requestFromUser(int i) {
        System.out.print(QuestionsToTheMenu[i]);

        scanner = new Scanner(System.in);
        String request = scanner.nextLine();

        request = request.toLowerCase().trim();
        lastRequestFromUser = request;
        if (requestToSubmenu(request) == 5) {
            return 5;
        }

        if (i > 2 && (request.toLowerCase()).replace(" ", "").equals("p")) {
            return 4;
        }
        if (validateRequest(request) && i < 3) {
            i++;
        }

        return i;
    }

    /**
     * funkcje do submenu
     * listowanie tylko litery
     * wywalenie while
     * walidacja do góry przed  submenu
     * printowanie czesci, a nie kategorii
     *
     * @param request
     */
    public static int requestToSubmenu(String request) {
        if (request.equals("koniec")) {
            System.out.println("Dziękujemy za skorzystanie z wyszukiwarki");
            System.exit(0);
        } else if (request.equals("model")) {
            System.out.println("Model to:");
            System.out.println(searchResults.get(1));
            System.exit(0);
        } else if (request.equals("gora")) {
//remove last el from searchres. oraz lastid i -1 lub false
        } else if (request.equals("silnik")) {
//remove last el from searchres. oraz lastid i -1 lub false
        } else if (request.equals("czesc")) {
//remove last el from searchres. oraz lastid i -1 lub false
        }
        return 0;
    }

    public static boolean validateRequest(String request) {

        //czy string alfanum
        if (!request.matches("[a-z0-9]*")) {
            System.out.println("Podałeś nieprawidłowe dane. Spróbój ponownie");
            requestFromUser(searchResults.size());
            return false;
        }
        if (request.matches("[a-z]")) {
            System.out.println("FUNKCJA OGRANICZANIA WYNIKÓW WYSZUKIWANIA. PRINT LITERA ALFABETU(pominięcie liter z argumentu) I ZWROT FALSE");
//            requestFromUser(searchResults.size());
            //zamiennik dla funkcji requestFromUser(searchResults.size());
            return false;
        }
        try {
            int numberInPrintedList = Integer.parseInt(request) - 1;
//            System.out.println(brands.get(numberInPrintedList).size()+"llllllll");

            if (numberInPrintedList <= referenceForTypeLists.size()) {
                lastSearchedNumberOnTheList = numberInPrintedList;
//jeśli jest kategoria to nie pobieraj z produktu tylko z kategorii!!!! Jest błąd w wydruku
                System.out.println(referenceForTypeLists.get(numberInPrintedList));
                if (referenceForTypeLists == brands) {
                    setSearchResults(brands.get(numberInPrintedList).getId());
                }else if (referenceForTypeLists == models) {
                    setSearchResults(models.get(numberInPrintedList).getId());
                }else if (referenceForTypeLists == carTypes) {
                    setSearchResults(carTypes.get(numberInPrintedList).getId());
                }else if (referenceForTypeLists == partCategory) {
                    setSearchResults(partCategory.get(numberInPrintedList).getId());
                }else if (referenceForTypeLists == part) {
                    setSearchResults(part.get(numberInPrintedList).getNumber());
                }
            } else {
//to do raportu gdzie indziej. Drukuje info przed listą
                System.out.println("Nie ma takiego elementu w bazie.");
                requestFromUser(searchResults.size());
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void setSearchResults(String element) {
        searchResults.add(element);
    }

    public static void getSearchResults() {
        for (String name : searchResults) {
//            if (referenceForTypeLists == brands) {
//                setSearchResults(brands.get(numberInPrintedList).getId());
//            }else if (referenceForTypeLists == models) {
//                setSearchResults(models.get(numberInPrintedList).getId());
//            }else if (referenceForTypeLists == carTypes) {
//                setSearchResults(carTypes.get(numberInPrintedList).getId());
//            }else if (referenceForTypeLists == partCategory) {
//                setSearchResults(partCategory.get(numberInPrintedList).getId());
//            }else if (referenceForTypeLists == part) {
//                setSearchResults(part.get(numberInPrintedList).getNumber());
//            }
            System.out.println("- " + name);
        }
    }


}

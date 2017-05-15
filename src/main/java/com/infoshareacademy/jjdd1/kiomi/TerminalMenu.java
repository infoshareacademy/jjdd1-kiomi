package com.infoshareacademy.jjdd1.kiomi;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.*;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;
import com.infoshareacademy.jjdd1.kiomi.app.statistics.StatisticDataBuilder;
import com.infoshareacademy.jjdd1.kiomi.app.services.PromotedBrandsLoader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.*;

public class TerminalMenu {

    static Scanner scanner;
    private static CarsDataLoader carsDataLoader = new CarsDataLoader();
    private static List<Brand> brands = new ArrayList();
    private static List<Model> models = new ArrayList();
    private static List<Type> carTypes = new ArrayList();
    private static List<PartCategory> partCategory = new ArrayList();
    private static List<Part> part = new ArrayList();
    private static List<?> referenceForTypeLists;
    private static final Logger LOGGER = LogManager.getLogger(TerminalMenu.class);

    public static void main(String[] args) throws IOException {



        try {
            startMenu();
        } catch (IOException e) {
            LOGGER.error("Brak pliku na serwerze!", e);
        }
    }

    private static String[] titleForListByData = {"Lista marek:", "Lista modeli:", "Lista typów silnika:", "Lista kategorii:", "Lista części:"};
    private static String[] QuestionsToTheMenu = {"Podaj markę:", "Podaj model:", "Podaj typ silnika:", "Podaj kategorię:", "Oto lista części pasujących do wyszukiwania."};

    public static List<String> getSearchResultsAsStrings() {
        return searchResultsAsStrings;
    }

    private static List<String> searchResults = new ArrayList();
    private static List<String> searchResultsAsStrings = new ArrayList();
    private static int lastSearchedNumberOnTheList;
    private static String lastRequestFromUser = "";
    private static int levelMenu = 0;

    public static void startMenu() throws IOException {
        System.out.println("Witaj w hurtownii części samochodowych");
        System.out.println("---------------------------------------");
        printListByData();


    }

    public static void printListByData() throws IOException {

        System.out.println(titleForListByData[getLevelMenu()]);

        if (getLevelMenu() < 4) {
            listByDataType();
            if (referenceForTypeLists.size() > 0) {
                printListByDataType(referenceForTypeLists);
            } else {
                setLevelMenu(4);
            }
        }
        if (getLevelMenu() == 4) {
            printPartsList();
        }

        reportForMenu();
        System.out.println("Jeśli lista jest za długa wpisz pierwszą literę wyszukiwanej frazy".toUpperCase());

        submenuForStartMenu();

        requestFromUser();

        operationsOnRequestFromTheUser();

        printListByData();

        scanner.close();

    }

    public static int getLevelMenu() {
        return levelMenu;
    }

    public static void setLevelMenu(int level) {
        levelMenu = level;
    }

    public static void listByDataType() throws IOException {
        if (brands.size() == 0) {
            brands = carsDataLoader.getBrandsList();
            referenceForTypeLists = brands;
            setLevelMenu(0);
        } else if (models.size() == 0) {
            String brandId = brands.get(lastSearchedNumberOnTheList).getId();
            models = carsDataLoader.getModelsListByIdForTerminal(brandId);
            referenceForTypeLists = models;
            setLevelMenu(1);
        } else if (carTypes.size() == 0) {
            String modelId = models.get(lastSearchedNumberOnTheList).getId();
            carTypes = carsDataLoader.getTypesListById(modelId);
            referenceForTypeLists = carTypes;
            setLevelMenu(2);
        } else {
            String carTypeId;
            if (partCategory.size() == 0) {
                carTypeId = carTypes.get(lastSearchedNumberOnTheList).getId();
            } else {
                carTypeId = partCategory.get(lastSearchedNumberOnTheList).getId();
            }
            partCategory = carsDataLoader.getPartCategoryListByIdForTerminal(carTypeId);
            referenceForTypeLists = partCategory;
            setLevelMenu(3);
        }
    }

    public static void printListByDataType(List data) {
        int i = 1;
        if (lastRequestFromUser.toString().matches("[a-z]")) {
            for (Object element : data) {
                if (element.toString().substring(0, 1).toLowerCase().equals(lastRequestFromUser)) {
                    System.out.println(i + " - " + element);
                }
                i++;
            }
        } else {
            for (Object element : data) {
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
            System.out.println("MODEL  :: Wpisz 'MODEL', aby wyświetlić info o modelu");
            System.out.println("SILNIK :: Wpisz 'SILNIK', aby wyświetlić info o silniku");
            System.out.println("RESET  :: Wpisz 'RESET', aby wyszukiwać od nowa");
            System.out.println("KONIEC :: Wpisz 'KONIEC', aby wyjść z programu");
        }
    }

    public static void reportForMenu() {
        if (searchResults.size() > 0) {
            System.out.println("---------------------------------------");
            System.out.println("Do tej pory wybrałeś: ");
            getSearchResults();
            System.out.println("---------------------------------------");
        }
    }

    private static void requestFromUser() {
        System.out.print(QuestionsToTheMenu[getLevelMenu()]);

        scanner = new Scanner(System.in);
        String request = scanner.nextLine();

        request = request.toLowerCase().trim();

        validateRequest(request);

        lastRequestFromUser = request;
        LOGGER.debug("Dane wprowadzone przez użytkownika: "+request);
    }

    public static void validateRequest(String request) {
        if (!request.matches("[a-z0-9]*")) {
            System.out.println("Podałeś nieprawidłowe dane. Spróbój ponownie");
            LOGGER.warn("Nieprawidłowy format danych wprowadzony przez użytkownika.");
            requestFromUser();
        }
    }

    public static void operationsOnRequestFromTheUser() throws IOException {
        try {
            int numberFromPrintedList = Integer.parseInt(lastRequestFromUser) - 1;

            if (numberFromPrintedList <= referenceForTypeLists.size()) {
                lastSearchedNumberOnTheList = numberFromPrintedList;
                setSearchResults(numberFromPrintedList);
                searchResultsAsStrings.add(referenceForTypeLists.get(numberFromPrintedList).toString());
            } else {
                System.out.println("Nie ma takiego elementu w bazie.");
                requestFromUser();
            }
        } catch (NumberFormatException e) {
            requestFromSubmenu(lastRequestFromUser);
        }
    }

    public static void setSearchResults(int element) {
        if (referenceForTypeLists == brands) {
            searchResults.add(brands.get(element).getId());
        } else if (referenceForTypeLists == models) {
            searchResults.add(models.get(element).getId());
        } else if (referenceForTypeLists == carTypes) {
            searchResults.add(carTypes.get(element).getId());
        } else if (referenceForTypeLists == partCategory) {
            searchResults.add(partCategory.get(element).getId());
        } else if (referenceForTypeLists == part) {
            searchResults.add(part.get(element).getNumber());
        }
    }

    public static void requestFromSubmenu(String request) throws IOException {

        if (request.length() == 1) {
            printListByDataType(referenceForTypeLists);
            requestFromUser();
            operationsOnRequestFromTheUser();
        } else if (request.equals("koniec")) {
            System.out.println("Dziękujemy za skorzystanie z wyszukiwarki");
            System.exit(0);
        } else if (request.equals("model")) {
            System.out.println("Dane modelu:");
            modelsMetrics();
            requestFromUser();
            operationsOnRequestFromTheUser();
        } else if (request.equals("silnik")) {
            System.out.println("Dane typu samochodu:");
            engineMetrics();
            requestFromUser();
            operationsOnRequestFromTheUser();
        } else if (request.equals("czesc")) {
            if (searchResults.size() == 0) {
                System.out.println("Listę części możesz wyświetlić tylko mając wybraną markę.");
                requestFromUser();
                operationsOnRequestFromTheUser();
            }
            lastRequestFromUser = "czesc";
            setLevelMenu(4);
        } else if (request.equals("reset")) {
            searchResults.clear();
            brands.clear();
            models.clear();
            carTypes.clear();
            partCategory.clear();
            part.clear();
            setLevelMenu(0);
            lastSearchedNumberOnTheList = 0;
            lastRequestFromUser = "";
            printListByData();
        } else {
            System.out.println("Polecenie nieobsługiwane przez system");
            requestFromUser();
            operationsOnRequestFromTheUser();
        }
    }

    public static void modelsMetrics() {
        if (getLevelMenu() > 1) {
            for (Model m : models) {
                if (m.getId() == searchResults.get(1)) {
                    System.out.println(m.getName());
                    System.out.println("Produkowany: " + m.getStart_month() + "/" + m.getStart_year() + " - " + m.getEnd_month() + "/" + m.getEnd_year());
                    System.out.println(m.getVehicle_group());
                }
            }
        } else {
            System.out.println("Nie wybrałeś jeszcze modelu");
        }
    }

    public static void engineMetrics() {
        if (getLevelMenu() > 2) {
            for (Type c : carTypes) {
                if (c.getId() == searchResults.get(2)) {
                    System.out.println(c.getName());
                    System.out.println(c.getBody());
                    System.out.println(c.getAxle());
                    System.out.println(c.getCcm());
                    System.out.println(c.getCylinders());
                    System.out.println(c.getFuel());
                    System.out.println(c.getEngine());
                    System.out.println(c.getKw());
                }
            }
        } else {
            System.out.println("Nie wybrałeś jeszcze typu silnika");
        }
    }

    public static void getSearchResults() {
        String searchResult = "";
        for (String name : searchResultsAsStrings) {
            searchResult +=name.toString();
        }
        LOGGER.debug("Wynik wyszukiwania: "+searchResult);

    }

    public static void printPartsList() throws IOException {
        part = carsDataLoader.getPartListById(searchResults.get(searchResults.size() - 1));
        PromotedBrandsLoader promotedBrandsLoader=new PromotedBrandsLoader();
        part = promotedBrandsLoader.rewritedPartListSorter(part);

        if (part.size() > 0) {
            printListByDataType(part);
        } else {
            System.out.println("Lista części dla tej kategorii jest pusta");
        }

        referenceForTypeLists = part;
        setLevelMenu(4);

    }

}

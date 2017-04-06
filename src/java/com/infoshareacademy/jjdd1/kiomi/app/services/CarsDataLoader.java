package com.infoshareacademy.jjdd1.kiomi.app.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Brand;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Model;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.PartCategory;
import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Type;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CarsDataLoader {
    private static final String BRANDS_URI="src/Resources/brands.json";
    private static final String MODELS_URI ="/FORD - modele.json";
    private static final String PART_URI="/FORD FOCUS III 1.5 TDCi XXDA - czйШci - hamulce - tarczowe.json";
    private static final String PARTCATEGORY_URI="/FORD FOCUS III 1.5 TDCi XXDA - czйШci.json";

    private String URI="/FORD FOCUS III - typy.json";

    Set<Brand> brand=new HashSet();
    Set<Model> model=new HashSet();
    Set<Type> type=new HashSet();
    Set<PartCategory> partcategory=new HashSet();


public void loadData() {
    Gson gson=new Gson();
    try {
        java.lang.reflect.Type listType = new TypeToken<List<Brand>>() {}.getType();
        Reader fileReader = new FileReader(BRANDS_URI);
        List<Brand> parsed = gson.fromJson(fileReader, listType);

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

}

}

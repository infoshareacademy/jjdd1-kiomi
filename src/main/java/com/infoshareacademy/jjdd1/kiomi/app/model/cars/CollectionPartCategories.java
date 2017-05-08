package com.infoshareacademy.jjdd1.kiomi.app.model.cars;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arek50 on 2017-04-22.
 */
public class CollectionPartCategories {
    private static final Logger LOGGER = LogManager.getLogger(CollectionPartCategories.class);
    private List<PartCategory> collectionPartCategories=new ArrayList<>();

    public List<PartCategory> getCollectionPartCategories() {
        return collectionPartCategories;
    }

    public void setCollectionPartCategories(List<PartCategory> collectionPartCategories) {
        this.collectionPartCategories = collectionPartCategories;
    }

    @Override
    public String toString() {
        return "CollectionPartCategories{" +
                "collectionPartCategories=" + collectionPartCategories +
                '}';
    }
}

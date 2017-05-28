package com.infoshareacademy.jjdd1.kiomi.app.statistics.promotedBrands;
import java.util.List;

/**
 * Created by marcin on 27.05.17.
 */
public interface IPromotedBrands {

    void addBrand (String promotedBrandToAdd);

    void removeBrand(String promotedBrandToRemove);

    List<PromotedBrands> getAllBrands();

}

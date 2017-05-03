package com.infoshareacademy.jjdd1.kiomi.$1_servicesTests;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Part;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;
import com.infoshareacademy.jjdd1.kiomi.app.services.PromotedBrandsLoader;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;

public class PromotedBrandsLoaderTests {

    PromotedBrandsLoader classUT = new PromotedBrandsLoader();

    @Test
    public void doesPromotedBrandListContainBrands () throws IOException {
        // when
        List<String> promotedListUT = classUT.promotedBrandsReader();

        // then
        assertFalse("PromotedBrandsList contains PromotedBrands.", promotedListUT.isEmpty());
    }

    @Test
    public void isPromotedBrandShownFirstInBrandsList () throws IOException {
        // given
        CarsDataLoader dataLoaderUT = new CarsDataLoader();
        String brandIdUT = "8ab";

        // when
        List<Part> listAfterSorting = null;
        try {
            listAfterSorting = classUT.rewritedPartListSorter(dataLoaderUT.getPartListById(brandIdUT));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> promotedListUT = classUT.promotedBrandsReader();

        // then
        assertTrue("After sorting BrandsList, PromotedBrand is first on the list", promotedListUT.get(0).equals(listAfterSorting.get(0).getBrand_clear()));
    }

}

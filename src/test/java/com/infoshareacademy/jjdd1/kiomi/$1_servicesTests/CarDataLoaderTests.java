package com.infoshareacademy.jjdd1.kiomi.$1_servicesTests;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.Part;
import com.infoshareacademy.jjdd1.kiomi.app.services.CarsDataLoader;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;

public class CarDataLoaderTests {

    @Test
    public void isCarDataLoaderWritingToPartsListWithSpecificID () throws IOException {
        // given
        CarsDataLoader dataLoaderUT = new CarsDataLoader();
        String givenIdUT = "8ab";

        // when
        List<Part> listComposedInCarsDataLoader = dataLoaderUT.getPartListById(givenIdUT);

        // then
        assertFalse("CarDataLoader write PartsList correctly.", listComposedInCarsDataLoader.isEmpty());
    }

}

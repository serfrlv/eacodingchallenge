package com.shine.eacodingchallenge.service.dataprocessor;

import com.shine.eacodingchallenge.domain.MakeData;
import com.shine.eacodingchallenge.domain.ModelData;
import com.shine.eacodingchallenge.domain.json.Car;
import com.shine.eacodingchallenge.domain.json.CarShow;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CarShowProcessorTest {

    @Test
    public void testProcessNoData(){
        test(null, null);
        test(new ArrayList<>(), new HashMap<>());
    }

    @Test
    public void testProcessValidObjects() {
        List<CarShow> sourceData = new ArrayList<>();
        CarShow show = new CarShow("show");
        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        car.setMake("make");
        car.setModel("model");
        cars.add(car);
        show.setCars(cars);
        sourceData.add(show);
        HashMap<String, MakeData> expectedResult = new HashMap<>();
        MakeData make = new MakeData("make");
        ModelData model = new ModelData("model");
        model.getAttendedCarShows().add("show");
        make.getModels().put("model", model);
        expectedResult.put("make", make);
        test(sourceData, expectedResult);
    }

    private void test(List<CarShow> sourceData, Map<String, MakeData> expectedResult) {
        CarShowProcessor carShowProcessor = new CarShowProcessor();
        try {
            Map<String, MakeData> makeData = carShowProcessor.generateMakeData(sourceData);
            if (expectedResult == null){
                Assert.assertNull(makeData);
            }
            else {
                Assert.assertNotNull(makeData);
                Assert.assertEquals(expectedResult.size(), makeData.size());
                Assert.assertEquals(expectedResult.keySet(), makeData.keySet());
                for (String make : makeData.keySet()) {
                    Assert.assertEquals(expectedResult.get(make), makeData.get(make));
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}

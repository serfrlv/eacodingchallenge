package com.shine.eacodingchallenge.service.dataprocessor;

import com.shine.eacodingchallenge.domain.ModelData;
import com.shine.eacodingchallenge.domain.MakeData;
import com.shine.eacodingchallenge.domain.json.Car;
import com.shine.eacodingchallenge.domain.json.CarShow;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple implementation of {@link ICarDataProcessor}
 */
@Service
public class CarShowProcessor implements ICarDataProcessor {

    /**
     * Takes a list of {@link CarShow} objects and generates a {@link Map} of {@link MakeData} stored against appropriate car make name
     * @param carShows
     * @return
     */
    public Map<String, MakeData> generateMakeData(List<CarShow> carShows) {

        if (carShows == null){
            return null;
        }

        Map<String, MakeData> makeData = new HashMap<>();
        if(carShows.size() > 0) {
            for (CarShow carShow : carShows) {
                String showName = carShow.getName();
                if (isEmpty(showName)) { //check if show data is valid
                    continue;
                }

                List<Car> cars = carShow.getCars();

                if (cars == null || cars.size() == 0) { //check if car records exist for this show
                    continue;
                }

                for (Car car : cars) {
                    String makeName = car.getMake();
                    String modelName = car.getModel();

                    if (isEmpty(makeName) || isEmpty(modelName)) { //check if car data is valid
                        continue;
                    }

                    MakeData makeDatum = makeData.get(makeName); //check if MakeData for this make already exist
                    if (makeDatum == null) {                    //if not, create MadeData object based on make name of the car
                        makeDatum = new MakeData(makeName);

                    }

                    Map<String, ModelData> models = makeDatum.getModels(); //check if ModelData for this model already exist for this make
                    ModelData modelData = models.get(modelName);           //if not, create ModelData object based on model name of the car
                    if (modelData == null) {
                        modelData = new ModelData(modelName);

                    }

                    //store created make & model objects
                    models.put(modelName, modelData);
                    makeData.put(makeName, makeDatum);

                    modelData.getAttendedCarShows().add(showName); //add car show to model's list
                }
            }
        }
        return makeData;
    }

    private boolean isEmpty(String string){
        return string == null || string.trim().equals("");
    }
}

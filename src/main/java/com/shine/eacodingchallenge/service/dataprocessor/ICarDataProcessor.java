package com.shine.eacodingchallenge.service.dataprocessor;

import com.shine.eacodingchallenge.domain.MakeData;
import com.shine.eacodingchallenge.domain.json.CarShow;

import java.util.List;
import java.util.Map;

/**
 * Defines a car data processor which generates a {@link Map} of {@link MakeData} based on a list of {@link CarShow} objects
 */
public interface ICarDataProcessor {

    Map<String, MakeData> generateMakeData(List<CarShow> carShows);

}

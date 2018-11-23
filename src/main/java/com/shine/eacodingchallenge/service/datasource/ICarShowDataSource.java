package com.shine.eacodingchallenge.service.datasource;

import com.shine.eacodingchallenge.domain.json.CarShow;

import java.util.List;

/**
 * A data source which provides access to car show data
 */
public interface ICarShowDataSource {

    /**
     * Loads all car shows form the data source
     * @return
     */
    List<CarShow> loadAll();
}

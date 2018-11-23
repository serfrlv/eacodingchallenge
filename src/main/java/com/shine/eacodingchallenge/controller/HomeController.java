package com.shine.eacodingchallenge.controller;

import com.shine.eacodingchallenge.service.datasource.ICarShowDataSource;
import com.shine.eacodingchallenge.service.dataprocessor.ICarDataProcessor;
import com.shine.eacodingchallenge.service.renderer.IVendorDataRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple rest controller to provide http access to the application output
 */
@RestController
public class HomeController {

    private ICarShowDataSource carShowDataSource;
    private ICarDataProcessor carDataProcessor;
    private IVendorDataRenderer vendorDataRenderer;

    @Autowired
    public HomeController(ICarShowDataSource carShowDataSource, ICarDataProcessor carDataProcessor, IVendorDataRenderer vendorDataRenderer) {
        this.carShowDataSource = carShowDataSource;
        this.carDataProcessor = carDataProcessor;
        this.vendorDataRenderer = vendorDataRenderer;
    }


    @RequestMapping("/")
    public String home(){
        return vendorDataRenderer.render(carDataProcessor.generateMakeData(carShowDataSource.loadAll()));
    }
}

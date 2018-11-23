package com.shine.eacodingchallenge;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Custom application properties bean
 */
@Component
@ConfigurationProperties(prefix = "rest")
public class RestProperties {

    /**
     * REST API url to load car show data from
     */
    private String url = "http://eacodingtest.digital.energyaustralia.com.au/api/v1/cars";

    /**
     * Number of retries when attempting to load car show data form url
     */
    private int retries = 5;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }
}

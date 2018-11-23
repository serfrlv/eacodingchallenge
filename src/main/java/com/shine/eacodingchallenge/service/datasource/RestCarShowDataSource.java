package com.shine.eacodingchallenge.service.datasource;

import com.shine.eacodingchallenge.RestProperties;
import com.shine.eacodingchallenge.domain.json.CarShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of {@link ICarShowDataSource} which accesses REST endpoint to retrieve {@link CarShow} objects as a json collection
 */
@Service
public class RestCarShowDataSource implements ICarShowDataSource {

    private RestProperties restProperties;
    private RestTemplate restTemplate;

    @Autowired
    public RestCarShowDataSource(RestProperties restProperties, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.restProperties = restProperties;
    }

    /**
     * Accesses REST endpoint specified by <code>rest.url</code> property and attempts to retrieve a collection of {@link CarShow} records in json format.<br/>
     * <code>rest.retries</code> parameter is used to specify the amount of allowed unsuccessful retrieval attempts before giving up;
     * @return a {@link List} of {@link CarShow} objects or null if it reached the maximum number of unsuccessful retrieval attempts
     */
    public List<CarShow> loadAll() {
        int retries = restProperties.getRetries();
        do {
            retries--;
            ResponseEntity<CarShow[]> responseEntity;
            try {
                responseEntity = restTemplate.getForEntity(restProperties.getUrl(), CarShow[].class); //attempting to access REST endpoint
            }
            catch (RestClientException e) { //json parsing error
                System.out.println("Invalid content, retrying.");
                continue;
            }
            catch (Throwable t) { //unexpected runtime exception
                t.printStackTrace();
                continue;
            }
            HttpStatus statusCode = responseEntity.getStatusCode();
            System.out.println(statusCode);
            System.out.println(responseEntity.getHeaders());
            if (statusCode.equals(HttpStatus.OK) && responseEntity.getHeaders() != null &&
                    responseEntity.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON)){ //check if get request was successful and content type is json
                CarShow[] carShows = responseEntity.getBody();
                System.out.println(carShows != null ? carShows : "<null>");
                if (carShows != null){
                    return Arrays.asList(carShows);
                }
            }
        }
        while (retries > 0); //return null if max number of failed attempts is reached
        return null;
    }
}


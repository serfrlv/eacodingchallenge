package com.shine.eacodingchallenge.service.datasource;

import com.shine.eacodingchallenge.RestProperties;
import com.shine.eacodingchallenge.domain.json.Car;
import com.shine.eacodingchallenge.domain.json.CarShow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(MockitoJUnitRunner.class)
public class RestCarShowDataSourceTest {

    @Mock
    private RestTemplate restTemplate;

    @Spy
    private RestProperties restProperties;

    @InjectMocks
    @Spy
    private RestCarShowDataSource restCarShowDataSource;

    @Before
    public void setup(){
        Mockito.when(restProperties.getRetries()).thenReturn(2);
        Mockito.when(restProperties.getUrl()).thenReturn("http://localhost/");
    }

    @Test
    public void testHttpError(){
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.CONTENT_TYPE, "text/html; charset=utf-8");
        test(new ResponseEntity("", headers, HttpStatus.BAD_REQUEST), null);
        test(new ResponseEntity("", headers, HttpStatus.INTERNAL_SERVER_ERROR), null);
        test(new ResponseEntity("", headers, HttpStatus.BAD_GATEWAY), null);
    }

    @Test
    public void testRestTemplateThrowsException(){
        Mockito.when(restTemplate.getForEntity(restProperties.getUrl(), CarShow[].class)).thenThrow(new RestClientException(""));
        List<CarShow> carShows = restCarShowDataSource.loadAll();
        Assert.assertEquals(null, carShows);
    }

    @Test
    public void testRestTemplateThrowsRuntimeException(){
        Mockito.when(restTemplate.getForEntity(restProperties.getUrl(), CarShow[].class)).thenThrow(new RuntimeException());
        List<CarShow> carShows = restCarShowDataSource.loadAll();
        Assert.assertEquals(null, carShows);
    }

    @Test
    public void testSuccessfulRestAccess(){
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        CarShow show1 = new CarShow("show1");
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("make1", "model1"));
        show1.setCars(cars);
        CarShow[] carShows = {show1};

        CarShow showE = new CarShow("show1");
        ArrayList<Car> carsE = new ArrayList<>();
        carsE.add(new Car("make1", "model1"));
        showE.setCars(carsE);

        test(new ResponseEntity(carShows, headers, HttpStatus.OK), Arrays.asList(new CarShow[]{showE}));

        System.out.println(new String("1").equals(""));
        System.out.println("1"=="1");
        System.out.println("1".equals("1"));
        String s1 = "123";
        String s2 = "123";
        String s3 = new String("123");
        String s4 = new StringBuilder("123").toString();
        System.out.println("s1==s2"+(s1==s2));
        System.out.println("s1==s3"+(s1==s3));
        System.out.println("s1==s4"+(s1==s4));
        System.out.println("s2==s3"+(s2==s3));
        System.out.println("s2==s4"+(s1==s4));
        System.out.println("s3==s4"+(s3==s4));
        System.out.println("s1.equals(s2)"+(s1.equals(s2)));
        System.out.println("s1.equals(s3)"+(s1.equals(s3)));
        System.out.println("s1.equals(s4)"+(s1.equals(s4)));
        System.out.println("s2.equals(s3)"+(s2.equals(s3)));
        System.out.println("s2.equals(s4)"+(s2.equals(s4)));
        System.out.println("s3.equals(s4)"+(s3.equals(s4)));
        List l = new ArrayList();
        Iterator i = l.iterator();
        while(((Iterator) i).hasNext()){
            i.next();
        }
        Set s =new HashSet();
        Object[] o = new Object[5];
        o[0] = new String("");
        o[1] = 1;

    }

    public void test(ResponseEntity restResponse, List<CarShow> expectedResult){
        Mockito.when(restTemplate.getForEntity(restProperties.getUrl(), CarShow[].class)).thenReturn(restResponse);
        List<CarShow> carShows = restCarShowDataSource.loadAll();
        Assert.assertEquals(expectedResult, carShows);
    }
}

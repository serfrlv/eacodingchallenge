package com.shine.eacodingchallenge.domain.json;

import java.util.List;
import java.util.Objects;

/**
 * represents car show json object
 */
public class CarShow {
   private String name;
   private List<Car> cars;

    public CarShow() {
    }

    public CarShow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    //generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarShow carShow = (CarShow) o;
        return Objects.equals(name, carShow.name) &&
                Objects.equals(cars, carShow.cars);
    }

    //generated
    @Override
    public int hashCode() {
        return Objects.hash(name, cars);
    }

    //generated
    @Override
    public String toString() {
        return "CarShow{" +
                "name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }
}

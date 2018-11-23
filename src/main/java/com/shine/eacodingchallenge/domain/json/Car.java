package com.shine.eacodingchallenge.domain.json;

import java.util.Objects;

/**
 * Represents the Car json object
 */
public class Car {
   private String make;
   private String model;

    public Car() {
    }

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    //generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(make, car.make) &&
                Objects.equals(model, car.model);
    }

    //generated
    @Override
    public int hashCode() {
        return Objects.hash(make, model);
    }

    //generated
    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
package com.shine.eacodingchallenge.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Domain object representing a car model with a set of car shows this model attended
 */
public class ModelData {
    private String name;
    private Set<String> attendedCarShows;

    public ModelData(String name) {
        this.name = name;
        clearAttendedCarShows();
    }

    public String getName() {
        return name;
    }

    public Set<String> getAttendedCarShows() {
        return attendedCarShows;
    }

    public void clearAttendedCarShows() {
        this.attendedCarShows = new HashSet<>();
    }

    //Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelData modelData = (ModelData) o;
        return Objects.equals(name, modelData.name) &&
                Objects.equals(attendedCarShows, modelData.attendedCarShows);
    }

    //Generated
    @Override
    public int hashCode() {
        return Objects.hash(name, attendedCarShows);
    }
}
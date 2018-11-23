package com.shine.eacodingchallenge.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Domain object representing car make with a collection of its car models
 */
public class MakeData {

    private String name;
    private Map<String, ModelData> models;

    public MakeData(String name) {
        this.name = name;
        clearModels();
    }

    public String getName() {
        return name;
    }

    public Map<String, ModelData> getModels() {
        return models;
    }

    public void clearModels() {
        this.models = new HashMap<>();
    }

    //Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MakeData makeData = (MakeData) o;
        return Objects.equals(name, makeData.name) &&
                Objects.equals(models, makeData.models);
    }

    //Generated
    @Override
    public int hashCode() {
        return Objects.hash(name, models);
    }
}
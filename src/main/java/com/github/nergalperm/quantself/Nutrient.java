package com.github.nergalperm.quantself;

public class Nutrient {
    private final String name;
    private final String measurementUnit;

    public Nutrient(String name, String measurementUnit) {
        this.name = name;
        this.measurementUnit = measurementUnit;
    }

    public String getName() {
        return name;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }
}

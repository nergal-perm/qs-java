package com.github.nergalperm.quantself;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class Nutrient {
    @Id
    private final String name;
    
    @Property
    private final String measurementUnit;
    
    @Relationship(type = "MEASURED_IN", direction = Relationship.Direction.OUTGOING)
    private Set<Rku> measurementRkus = new HashSet<>();
    
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
    
    public Set<Rku> getMeasurementRkus() {
        return measurementRkus;
    }
    
    public void setMeasurementRkus(Set<Rku> measurementRkus) {
        this.measurementRkus = measurementRkus;
    }
}

package com.github.nergalperm.quantself;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class Ingredient {
    @Id
    private final String name;
    
    @Relationship(type = "MEASURED_IN", direction = Relationship.Direction.OUTGOING)
    private Set<Rku> measurementRkus = new HashSet<>();
    
    public Ingredient(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Set<Rku> getMeasurementRkus() {
        return measurementRkus;
    }
    
    public void setMeasurementRkus(Set<Rku> measurementRkus) {
        this.measurementRkus = measurementRkus;
    }
}
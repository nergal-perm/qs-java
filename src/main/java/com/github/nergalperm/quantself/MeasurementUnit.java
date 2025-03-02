package com.github.nergalperm.quantself;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class MeasurementUnit {
    @Id
    private final String name;
    
    @Relationship(type = "MEASURES", direction = Relationship.Direction.OUTGOING)
    private Set<Rku> measuredRkus = new HashSet<>();
    
    public MeasurementUnit(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Set<Rku> getMeasuredRkus() {
        return measuredRkus;
    }
    
    public void setMeasuredRkus(Set<Rku> measuredRkus) {
        this.measuredRkus = measuredRkus;
    }
}
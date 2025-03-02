package com.github.nergalperm.quantself;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Node
public class Dish {
    @Id
    private final String name;
    
    @Property
    private LocalDate cookedOn;
    
    @Relationship(type = "CONTAINER_OF", direction = Relationship.Direction.OUTGOING)
    private Set<Composition> compositions = new HashSet<>();
    
    public Dish(String name, LocalDate cookedOn) {
        this.name = name;
        this.cookedOn = cookedOn;
    }
    
    public String getName() {
        return name;
    }
    
    public LocalDate getCookedOn() {
        return cookedOn;
    }
    
    public void setCookedOn(LocalDate cookedOn) {
        this.cookedOn = cookedOn;
    }
    
    public Set<Composition> getCompositions() {
        return compositions;
    }
    
    public void setCompositions(Set<Composition> compositions) {
        this.compositions = compositions;
    }
}
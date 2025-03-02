package com.github.nergalperm.quantself;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class Rku {
    @Id
    private String name;
    
    @Relationship(type = "COMPONENT_OF", direction = Relationship.Direction.OUTGOING)
    private Set<Composition> componentOf = new HashSet<>();
    
    public Rku() {
    }
    
    public Rku(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Composition> getComponentOf() {
        return componentOf;
    }
    
    public void setComponentOf(Set<Composition> componentOf) {
        this.componentOf = componentOf;
    }
}
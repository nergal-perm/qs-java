package com.github.nergalperm.quantself;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Node
public class Composition {
    @Id
    private final String name;

    public Composition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @RelationshipProperties
    public static class CompositionRelation {
        @Id @GeneratedValue
        private Long id;

        private Double amount;
        
        @TargetNode
        private Composition composition;
        
        public CompositionRelation(Double amount, Composition composition) {
            this.amount = amount;
            this.composition = composition;
        }
        
        public Double getAmount() {
            return amount;
        }
        
        public void setAmount(Double amount) {
            this.amount = amount;
        }
        
        public Composition getComposition() {
            return composition;
        }
        
        public void setComposition(Composition composition) {
            this.composition = composition;
        }
    }
}
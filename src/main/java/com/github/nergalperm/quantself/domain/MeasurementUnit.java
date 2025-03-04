package com.github.nergalperm.quantself.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * Represents a unit of measurement for food quantities.
 */
@Node("MeasurementUnit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementUnit {

    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private String symbol;
    private UnitType type;
    private Double conversionFactor;

    public enum UnitType {
        WEIGHT,
        VOLUME,
        COUNT,
        OTHER
    }

    public MeasurementUnit(String name, String symbol, UnitType type, Double conversionFactor) {
        this.name = name;
        this.symbol = symbol;
        this.type = type;
        this.conversionFactor = conversionFactor;
    }
}
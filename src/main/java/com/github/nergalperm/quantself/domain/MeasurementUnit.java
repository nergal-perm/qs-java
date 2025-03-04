package com.github.nergalperm.quantself.domain;

import java.util.UUID;
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
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private UUID id;
    
    private String name;
    private String symbol;
    private UnitType type;

    public enum UnitType {
        WEIGHT,
        VOLUME,
        COUNT,
        OTHER
    }

    public MeasurementUnit(String name, String symbol, UnitType type) {
        this.name = name;
        this.symbol = symbol;
        this.type = type;
    }
}
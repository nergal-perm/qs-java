package com.github.nergalperm.quantself.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementUnitTest {

    @Test
    void testMeasurementUnitCreation() {
        MeasurementUnit unit = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT);
        
        assertEquals("Gram", unit.getName());
        assertEquals("g", unit.getSymbol());
        assertEquals(MeasurementUnit.UnitType.WEIGHT, unit.getType());
    }

    @Test
    void testEqualsAndHashCode() {
        MeasurementUnit unit1 = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT);
        MeasurementUnit unit2 = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT);
        
        // IDs will be null, but names, symbols and types are equal
        assertEquals(unit1, unit2);
        assertEquals(unit1.hashCode(), unit2.hashCode());
    }
}
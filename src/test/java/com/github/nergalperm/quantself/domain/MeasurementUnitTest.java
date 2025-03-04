package com.github.nergalperm.quantself.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementUnitTest {

    @Test
    void testMeasurementUnitCreation() {
        MeasurementUnit unit = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT, 1.0);
        
        assertEquals("Gram", unit.getName());
        assertEquals("g", unit.getSymbol());
        assertEquals(MeasurementUnit.UnitType.WEIGHT, unit.getType());
        assertEquals(1.0, unit.getConversionFactor());
    }

    @Test
    void testEqualsAndHashCode() {
        MeasurementUnit unit1 = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT, 1.0);
        MeasurementUnit unit2 = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT, 1.0);
        
        // IDs will be null, but names, symbols and types are equal
        assertEquals(unit1, unit2);
        assertEquals(unit1.hashCode(), unit2.hashCode());
    }
}
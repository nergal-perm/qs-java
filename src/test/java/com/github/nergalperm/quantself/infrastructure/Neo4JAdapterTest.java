package com.github.nergalperm.quantself.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import java.util.List;
import org.junit.jupiter.api.Test;

class Neo4JAdapterTest {

    @Test
    void testNullableWrapperWithEmptyData() {
        Neo4jAdapter wrapper = Neo4jAdapter.createNull();
        assertTrue(wrapper.findAllMeasurementUnits().isEmpty());
    }

    @Test
    void testNullableWrapperWithStubData() {
        MeasurementUnit gram = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT);
        Neo4jAdapter wrapper = Neo4jAdapter.createNull(List.of(gram));
        
        List<MeasurementUnit> units = wrapper.findAllMeasurementUnits();
        assertFalse(units.isEmpty());
        assertEquals(1, units.size());
        assertEquals("Gram", units.getFirst().getName());
    }

    @Test
    void testNullableSave() {
        Neo4jAdapter wrapper = Neo4jAdapter.createNull();
        MeasurementUnit ml = new MeasurementUnit("Milliliter", "ml", MeasurementUnit.UnitType.VOLUME);
        
        MeasurementUnit saved = wrapper.saveMeasurementUnit(ml);
        assertNotNull(saved.getId());
        assertEquals(1L, saved.getId());
        
        List<MeasurementUnit> found = wrapper.findAllMeasurementUnits();
        assertFalse(found.isEmpty());
        assertEquals("Milliliter", found.getFirst().getName());
    }
}
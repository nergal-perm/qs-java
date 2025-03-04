package com.github.nergalperm.quantself.infrastructure;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("Gram", units.get(0).getName());
    }

    @Test
    void testNullableSave() {
        Neo4jAdapter wrapper = Neo4jAdapter.createNull();
        MeasurementUnit ml = new MeasurementUnit("Milliliter", "ml", MeasurementUnit.UnitType.VOLUME);
        
        MeasurementUnit saved = wrapper.saveMeasurementUnit(ml);
        assertNotNull(saved.getId());
        assertEquals(1L, saved.getId());
        
        Optional<MeasurementUnit> found = wrapper.findMeasurementUnitById(1L);
        assertTrue(found.isPresent());
        assertEquals("Milliliter", found.get().getName());
    }
}
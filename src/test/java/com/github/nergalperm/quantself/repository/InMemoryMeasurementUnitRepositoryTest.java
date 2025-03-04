package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMeasurementUnitRepositoryTest {

    private InMemoryMeasurementUnitRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryMeasurementUnitRepository();
    }

    @Test
    void shouldGenerateIdsForNewEntities() {
        MeasurementUnit gram = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT);
        MeasurementUnit ml = new MeasurementUnit("Milliliter", "ml", MeasurementUnit.UnitType.VOLUME);
        
        gram = repository.save(gram);
        ml = repository.save(ml);
        
        assertNotNull(gram.getId());
        assertNotNull(ml.getId());
        assertNotEquals(gram.getId(), ml.getId());
    }

    @Test
    void shouldFindAllSavedEntities() {
        MeasurementUnit gram = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT);
        MeasurementUnit ml = new MeasurementUnit("Milliliter", "ml", MeasurementUnit.UnitType.VOLUME);
        
        repository.saveAll(List.of(gram, ml));
        
        List<MeasurementUnit> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.stream().anyMatch(u -> u.getName().equals("Gram")));
        assertTrue(all.stream().anyMatch(u -> u.getName().equals("Milliliter")));
    }

    @Test
    void shouldDeleteEntities() {
        MeasurementUnit gram = new MeasurementUnit("Gram", "g", MeasurementUnit.UnitType.WEIGHT);
        gram = repository.save(gram);
        
        repository.deleteById(gram.getId());
        
        assertTrue(repository.findAll().isEmpty());
    }
}
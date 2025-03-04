package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.lang.NonNull;

public class InMemoryMeasurementUnitRepository implements MeasurementUnitRepository {
    private final Map<UUID, MeasurementUnit> storage = new ConcurrentHashMap<>();

    @Override
    @NonNull
    public MeasurementUnit save(@NonNull MeasurementUnit entity) {
        if (entity.getId() == null) {
            entity.setId(new GeneratedValue.UUIDGenerator().generateId("MeasurementUnit", entity));
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    @NonNull
    public List<MeasurementUnit> findAll() {
        return new ArrayList<>(storage.values());
    }
}
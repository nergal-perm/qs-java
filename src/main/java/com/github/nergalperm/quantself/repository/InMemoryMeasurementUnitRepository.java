package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryMeasurementUnitRepository implements MeasurementUnitRepository {
    private final Map<Long, MeasurementUnit> storage = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    @NonNull
    public <S extends MeasurementUnit> S save(@NonNull S entity) {
        if (entity.getId() == null) {
            entity.setId(idSequence.getAndIncrement());
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
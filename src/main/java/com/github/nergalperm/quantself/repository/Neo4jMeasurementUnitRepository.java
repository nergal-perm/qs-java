package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import java.util.List;
import org.springframework.lang.NonNull;

public class Neo4jMeasurementUnitRepository implements MeasurementUnitRepository {
    private final GraphMeasurementUnitRepository delegate;

    public Neo4jMeasurementUnitRepository(GraphMeasurementUnitRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    @NonNull
    public MeasurementUnit save(@NonNull MeasurementUnit entity) {
        return delegate.save(entity);
    }

    @Override
    @NonNull
    public List<MeasurementUnit> findAll() {
        return delegate.findAll();
    }
}
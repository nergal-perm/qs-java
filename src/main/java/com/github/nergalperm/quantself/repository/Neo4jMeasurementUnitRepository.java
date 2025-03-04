package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import com.github.nergalperm.quantself.repository.internal.Neo4jMeasurementUnitInternalRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Neo4jMeasurementUnitRepository implements MeasurementUnitRepository {
    private final Neo4jMeasurementUnitInternalRepository delegate;

    public Neo4jMeasurementUnitRepository(Neo4jMeasurementUnitInternalRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    @NonNull
    public <S extends MeasurementUnit> S save(@NonNull S entity) {
        return delegate.save(entity);
    }

    @Override
    @NonNull
    public List<MeasurementUnit> findAll() {
        return delegate.findAll();
    }
}
package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import org.springframework.lang.NonNull;

import java.util.List;

public interface MeasurementUnitRepository {
    @NonNull
    <S extends MeasurementUnit> S save(@NonNull S entity);
    
    @NonNull
    List<MeasurementUnit> findAll();
}
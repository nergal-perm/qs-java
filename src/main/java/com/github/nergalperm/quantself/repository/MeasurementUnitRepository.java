package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import java.util.List;
import org.springframework.lang.NonNull;

public interface MeasurementUnitRepository {
    @NonNull
    MeasurementUnit save(@NonNull MeasurementUnit entity);

    @NonNull
    List<MeasurementUnit> findAll();
}
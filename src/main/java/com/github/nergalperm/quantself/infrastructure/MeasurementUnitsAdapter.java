package com.github.nergalperm.quantself.infrastructure;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import com.github.nergalperm.quantself.repository.InMemoryMeasurementUnitRepository;
import com.github.nergalperm.quantself.repository.MeasurementUnitRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MeasurementUnitsAdapter {
    private final MeasurementUnitRepository repository;

    public static MeasurementUnitsAdapter create(MeasurementUnitRepository repository) {
        return new MeasurementUnitsAdapter(repository);
    }

    public static MeasurementUnitsAdapter createNull() {
        return new MeasurementUnitsAdapter(new InMemoryMeasurementUnitRepository());
    }

    public static MeasurementUnitsAdapter createNull(List<MeasurementUnit> stubData) {
        MeasurementUnitsAdapter adapter = createNull();
        stubData.forEach(adapter::saveMeasurementUnit);
        return adapter;
    }

    public List<MeasurementUnit> findAllMeasurementUnits() {
        return repository.findAll();
    }

    public MeasurementUnit saveMeasurementUnit(MeasurementUnit unit) {
        return repository.save(unit);
    }
}
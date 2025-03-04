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
public class Neo4jAdapter {
    private final MeasurementUnitRepository repository;

    // Production factory
    public static Neo4jAdapter create(MeasurementUnitRepository repository) {
        return new Neo4jAdapter(repository);
    }

    // Nullable factory with optional stub data
    public static Neo4jAdapter createNull() {
        return new Neo4jAdapter(new InMemoryMeasurementUnitRepository());
    }

    public static Neo4jAdapter createNull(List<MeasurementUnit> stubData) {
        Neo4jAdapter wrapper = createNull();
        stubData.forEach(wrapper::saveMeasurementUnit);
        return wrapper;
    }

    public List<MeasurementUnit> findAllMeasurementUnits() {
        return repository.findAll();
    }

    public MeasurementUnit saveMeasurementUnit(MeasurementUnit unit) {
        return repository.save(unit);
    }
}
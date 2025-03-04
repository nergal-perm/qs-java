package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import java.util.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.neo4j.repository.Neo4jRepository;

@ConditionalOnProperty(name = "neo4j.enabled", havingValue = "true")
public interface GraphMeasurementUnitRepository extends Neo4jRepository<MeasurementUnit, UUID> {
}

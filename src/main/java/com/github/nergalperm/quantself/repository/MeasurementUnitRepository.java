package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MeasurementUnitRepository extends Neo4jRepository<MeasurementUnit, Long> {
}
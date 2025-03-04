package com.github.nergalperm.quantself.repository.internal;

import com.github.nergalperm.quantself.domain.MeasurementUnit;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Neo4jMeasurementUnitInternalRepository extends Neo4jRepository<MeasurementUnit, Long> {
}
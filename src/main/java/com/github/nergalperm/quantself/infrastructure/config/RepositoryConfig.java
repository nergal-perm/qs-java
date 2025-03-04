package com.github.nergalperm.quantself.infrastructure.config;

import com.github.nergalperm.quantself.repository.GraphMeasurementUnitRepository;
import com.github.nergalperm.quantself.repository.InMemoryMeasurementUnitRepository;
import com.github.nergalperm.quantself.repository.MeasurementUnitRepository;
import com.github.nergalperm.quantself.repository.Neo4jMeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RepositoryConfig {
    @Value("${neo4j.enabled}")
    private Boolean neo4jEnabled;

    @Bean
    @Primary
    @ConditionalOnProperty(name = "neo4j.enabled", havingValue = "false", matchIfMissing = true)
    public MeasurementUnitRepository inMemoryRepository() {
        return new InMemoryMeasurementUnitRepository();
    }

    @Bean
    @ConditionalOnProperty(name = "neo4j.enabled", havingValue = "true")
    public MeasurementUnitRepository neo4jRepository(GraphMeasurementUnitRepository internalRepository) {
            return new Neo4jMeasurementUnitRepository(internalRepository);
    }
}
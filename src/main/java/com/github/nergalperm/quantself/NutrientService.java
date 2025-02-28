package com.github.nergalperm.quantself;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutrientService {

    private final Driver driver;

    public NutrientService(Driver driver) {
        this.driver = driver;
    }

    public List<Nutrient> getAllNutrients() {
        try (Session session = driver.session()) {
            return session.run("MATCH (n:Nutrient)-[:MEASURED_IN]->(:Rku)<-[:MEASURES]-(unit:MeasurementUnit) "
                    + "RETURN n.name AS name, unit.name AS measurementUnit")
                .list(record -> new Nutrient(
                        record.get("name").asString(),
                        record.get("measurementUnit").asString()
                    )
                );
        }
    }
}
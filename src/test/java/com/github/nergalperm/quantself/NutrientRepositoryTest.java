package com.github.nergalperm.quantself;

import com.github.nergalperm.quantself.repository.NutrientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class NutrientRepositoryTest {

    @Container
    private static final Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:5.25.1")
            .withoutAuthentication();

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
    }

    @Autowired
    private NutrientRepository nutrientRepository;

    @Test
    void testCreateAndRetrieveNutrient() {
        // Create a nutrient
        Nutrient protein = new Nutrient("Protein", "g");
        nutrientRepository.save(protein);

        // Retrieve by ID
        Optional<Nutrient> foundNutrient = nutrientRepository.findById("Protein");
        assertThat(foundNutrient).isPresent();
        assertThat(foundNutrient.get().getName()).isEqualTo("Protein");
        assertThat(foundNutrient.get().getMeasurementUnit()).isEqualTo("g");

        // Retrieve all nutrients
        List<Nutrient> allNutrients = nutrientRepository.findAllNutrients();
        assertThat(allNutrients).isNotEmpty();
        assertThat(allNutrients.stream().anyMatch(n -> n.getName().equals("Protein"))).isTrue();
    }
}
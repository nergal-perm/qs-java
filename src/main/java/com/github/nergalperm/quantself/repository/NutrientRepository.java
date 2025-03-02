package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.Nutrient;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutrientRepository extends Neo4jRepository<Nutrient, String> {
    
    List<Nutrient> findByName(String name);
    
    @Query("MATCH (n:Nutrient) RETURN n ORDER BY n.name")
    List<Nutrient> findAllNutrients();
}
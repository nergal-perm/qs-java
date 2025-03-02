package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.Ingredient;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends Neo4jRepository<Ingredient, String> {
    
    List<Ingredient> findByName(String name);
    
    @Query("MATCH (i:Ingredient) RETURN i ORDER BY i.name")
    List<Ingredient> findAllIngredients();
}
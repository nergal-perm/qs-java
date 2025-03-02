package com.github.nergalperm.quantself.repository;

import com.github.nergalperm.quantself.Dish;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DishRepository extends Neo4jRepository<Dish, String> {
    
    List<Dish> findByName(String name);
    
    List<Dish> findByCookedOn(LocalDate cookedOn);
    
    @Query("MATCH (d:Dish) RETURN d ORDER BY d.name")
    List<Dish> findAllDishes();
}
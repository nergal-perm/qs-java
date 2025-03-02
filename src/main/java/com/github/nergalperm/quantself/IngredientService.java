package com.github.nergalperm.quantself;

import com.github.nergalperm.quantself.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAllIngredients();
    }
    
    public Optional<Ingredient> getIngredientByName(String name) {
        return ingredientRepository.findById(name);
    }
    
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
    
    public void deleteIngredient(String name) {
        ingredientRepository.deleteById(name);
    }
}
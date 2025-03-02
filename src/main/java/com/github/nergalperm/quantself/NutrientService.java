package com.github.nergalperm.quantself;

import com.github.nergalperm.quantself.repository.NutrientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutrientService {
    private final NutrientRepository nutrientRepository;
    
    public NutrientService(NutrientRepository nutrientRepository) {
        this.nutrientRepository = nutrientRepository;
    }
    
    public List<Nutrient> getAllNutrients() {
        return nutrientRepository.findAllNutrients();
    }
    
    public Optional<Nutrient> getNutrientByName(String name) {
        return nutrientRepository.findById(name);
    }
    
    public Nutrient saveNutrient(Nutrient nutrient) {
        return nutrientRepository.save(nutrient);
    }
    
    public void deleteNutrient(String name) {
        nutrientRepository.deleteById(name);
    }
}
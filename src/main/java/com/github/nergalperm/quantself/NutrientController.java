package com.github.nergalperm.quantself;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class NutrientController {

    private final NutrientService nutrientService;
    private final IngredientService ingredientService;

    public NutrientController(NutrientService nutrientService, IngredientService ingredientService) {
        this.nutrientService = nutrientService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("nutrients", nutrientService.getAllNutrients());
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        return "index";
    }

    @GetMapping("/api/nutrients")
    @ResponseBody
    public List<Nutrient> getAllNutrients() {
        return nutrientService.getAllNutrients();
    }

    @GetMapping("/api/nutrients/{name}")
    @ResponseBody
    public ResponseEntity<Nutrient> getNutrientByName(@PathVariable String name) {
        Optional<Nutrient> nutrient = nutrientService.getNutrientByName(name);
        return nutrient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/nutrients")
    @ResponseBody
    public Nutrient createNutrient(@RequestBody Nutrient nutrient) {
        return nutrientService.saveNutrient(nutrient);
    }

    @DeleteMapping("/api/nutrients/{name}")
    @ResponseBody
    public ResponseEntity<Void> deleteNutrient(@PathVariable String name) {
        nutrientService.deleteNutrient(name);
        return ResponseEntity.ok().build();
    }
}
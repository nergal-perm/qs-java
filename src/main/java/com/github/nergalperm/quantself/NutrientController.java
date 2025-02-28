package com.github.nergalperm.quantself;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NutrientController {

    private final NutrientService nutrientService;

    public NutrientController(NutrientService nutrientService) {
        this.nutrientService = nutrientService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("nutrients", nutrientService.getAllNutrients());
        return "index";
    }
}
package com.github.nergalperm.quantself.controller;

import com.github.nergalperm.quantself.infrastructure.MeasurementUnitsAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/units")
@RequiredArgsConstructor
public class MeasurementUnitsController {
    private final MeasurementUnitsAdapter measurementUnitsAdapter;

    @GetMapping
    public String listUnits(Model model) {
        model.addAttribute("units", measurementUnitsAdapter.findAllMeasurementUnits());
        return "units/list";
    }
}
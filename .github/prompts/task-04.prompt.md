# Implement Nutrient Calculator Service

Use #file:./generic.prompt.md as a basic prompt, please.

## Extended Description
Build a dedicated service that computes total nutrient values based on logged ingredient portions. This service will execute dynamic calculations upon form submission to update nutritional totals.

## Technical Details
- Create a `NutrientCalculator` class with a method that calculates nutrients using the formula:

$$
\text{Total Nutrient} = \left(\frac{\text{PortionSizeInGrams}}{100}\right) \times \text{NutrientPer100g}
$$

- Validate user inputs with constraints such as `@Min(0.1)` and `@Max(10000)` to avoid unrealistic values.
- Write unit tests ensuring the service handles edge cases and returns accurate nutrient computations.
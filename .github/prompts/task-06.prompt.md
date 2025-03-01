# Build Core Thymeleaf Templates for PoC UI

## Extended Description
Develop the primary front-end views and layout using Thymeleaf templates to enable food logging and display daily nutrient summaries. This template setup supports the static nutritional goals and the basic UI for the PoC.

## Technical Details
- Create a base layout template (`layout.html`) with common headers, footers, and styles.
- Develop reusable fragments such as `ingredientForm.html` for food entry and `dailyLog.html` for viewing nutrient totals.
- Leverage Thymeleaf's `th:object` attribute for Spring model binding to facilitate seamless data integration.
- Integrate inline scripts for HTMX to ensure dynamic interactions on the pages.
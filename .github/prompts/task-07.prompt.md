# Integrate End-to-End Ingredient Logging Flow

Use #file:./generic.prompt.md as a basic prompt, please.

## Extended Description
Wire together the front-end, back-end, and database to deliver a complete ingredient logging workflow—from searching for ingredients and entering portion details to calculating nutrients and confirming the log entry.

## Technical Details
- Develop an HTML form (using Thymeleaf) for logging entries that includes the dynamic search and portion input fields.
- Create a POST endpoint (e.g., `/log-entry`) that processes the log entry and triggers the `NutrientCalculator` service.
- Ensure transactional integrity by annotating the logging method with `@Transactional` so that all steps either complete successfully or roll back in case of failure.
- Use HTMX to update the UI with confirmation messages and updated daily nutrient totals upon successful logging.
- Conduct integration tests to simulate the full ingredient logging cycle.
Use #file:.github/prompts/generic.prompt.md as a high-level prompt, please.

# User Story: View All Measurement Units

## Description
As a user tracking my food consumption, I need to see all available measurement units in the system so that I can accurately record quantities of ingredients and food items I consume.

## Acceptance Criteria
- A dedicated page or section displays all measurement units stored in the database
- Each measurement unit shows its name, symbol/abbreviation, and type (weight, volume, etc.)
- The units are displayed in an organized manner (alphabetical order or grouped by type)
- The page loads quickly (under 2 seconds)

## Technical Details

### Data Model
- Create a `MeasurementUnit` entity with the following properties:
  - `id`: Unique identifier
  - `name`: Full name of the measurement unit (e.g., "Gram", "Milliliter")
  - `symbol`: Abbreviation or symbol (e.g., "g", "ml")
  - `type`: The category of measurement (e.g., WEIGHT, VOLUME, COUNT)
  - `conversionFactor`: Factor for converting to a base unit (optional for future use)

### Backend Implementation (Spring Boot)
1. Create the `MeasurementUnit` domain entity class
2. Implement Neo4j repository for `MeasurementUnit`
3. Create a service layer to handle measurement unit operations
4. Develop a REST controller with endpoint to retrieve all units
5. Implement a controller method for the Thymeleaf template view

### Frontend Implementation (Thymeleaf + HTMX)
1. Create a Thymeleaf template to display all measurement units
2. Add basic styling for readability
3. Implement simple HTMX interaction for any dynamic features (filtering/sorting)

### Implementation Steps (Following MMMSS Approach)
1. Create the domain model and repository
2. Add service layer implementation
3. Create controller and HTML template

### Testing Requirements
- Unit tests for the repository and service layers
- Integration test to verify endpoint functionality

This feature will provide the foundation for food tracking as it establishes the units of measurement that will be used throughout the application.
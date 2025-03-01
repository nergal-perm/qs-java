# Develop Ingredient CRUD API Endpoints

## Extended Description
Implement RESTful endpoints to enable creating, retrieving, updating, and deleting ingredient records. These endpoints will support the basic ingredient logging flow of the PoC.

## Technical Details
- Develop an `IngredientController` with endpoints:
    - **POST** `/api/ingredients` – Create a new ingredient
    - **GET** `/api/ingredients/{id}` – Retrieve an ingredient by ID
    - **PUT** `/api/ingredients/{id}` – Update an existing ingredient
    - **DELETE** `/api/ingredients/{id}` – Remove an ingredient
- Utilize Spring MVC and integrate HTMX for returning HTML fragments where applicable.
- Apply Hibernate Validator annotations (e.g., `@NotNull`, `@Min`) for validating incoming data.
- Implement exception handling with `@ControllerAdvice` to provide meaningful error messages.
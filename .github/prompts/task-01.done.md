# Configure Neo4j Database Instance for PoC

Use #file:./generic.prompt.md as a basic prompt, please.

## Extended Description
Set up and configure a Neo4j instance using Docker Compose that provides the necessary graph schema for storing ingredients, dishes, and their relationships. This task lays the foundation for data persistence in the food tracker.

## Technical Details
- Create a Docker Compose file to run a Neo4j container with port mappings (7474 for HTTP, 7687 for Bolt).
- Define environment variables (e.g., `NEO4J_AUTH`) and volume mapping for data persistence.
- Make the Neo4j container run the script that clears the database and then populates it with sample data. Script is located in #file:../../scripts/sample-food-tracker.cypher  
- Develop Spring Data Neo4j domain models annotated with `@NodeEntity` (e.g., for Ingredient and Dish).
- Implement basic Cypher queries and tests using `@DataNeo4jTest` to validate connectivity.
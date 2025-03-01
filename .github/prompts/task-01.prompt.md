# Configure Neo4j Database Instance for PoC

## Extended Description
Set up and configure a Neo4j instance using Docker Compose that provides the necessary graph schema for storing ingredients, dishes, and their relationships. This task lays the foundation for data persistence in the food tracker.

## Technical Details
- Create a Docker Compose file to run a Neo4j container with port mappings (7474 for HTTP, 7687 for Bolt).
- Define environment variables (e.g., `NEO4J_AUTH`) and volume mapping for data persistence.
- Develop Spring Data Neo4j domain models annotated with `@NodeEntity` (e.g., for Ingredient and Dish).
- Implement basic Cypher queries and tests using `@DataNeo4jTest` to validate connectivity.
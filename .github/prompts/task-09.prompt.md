# Configure Local Development Environment with Docker Compose

## Extended Description
Establish a reproducible local development environment using Docker Compose that orchestrates both the Neo4j database and the SpringBoot application. This setup accelerates development and testing cycles.

## Technical Details
- Write a Docker Compose YAML file that defines two services: one for Neo4j and one for the SpringBoot application.
- Ensure proper port mappings and dependency specification using `depends_on`.
- Configure environment variables and volume mappings for persistent storage.
- Validate the setup by running `docker-compose up` and verifying connectivity between services.

```yaml
services:
  neo4j:
    image: neo4j:5.13
    ports:
      - "7474:7474"
      - "7687:7687"
    volumes:
      - neo4j_data:/data
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - neo4j
volumes:
  neo4j_data:
```
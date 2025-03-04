# Configure Local Development Environment with Docker Compose

Use #file:./generic-prompt.md as a basic prompt, please.

## Extended Description
Establish a reproducible local development environment using Docker Compose that orchestrates both the Neo4j database and the SpringBoot application. This setup accelerates development and testing cycles.

## Technical Details
- Write a Docker Compose YAML file that defines two services: one for Neo4j and one for the SpringBoot application.
- Ensure proper port mappings and dependency specification using `depends_on`.
- Configure environment variables and volume mappings for persistent storage.
- Make sure that the database container runs the initializing script on every start. Ask the user for the script location in the project directory.
- Validate the setup by running `docker-compose up` and verifying connectivity between services.
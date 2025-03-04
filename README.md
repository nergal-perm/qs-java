# qs-java

Quantified Self with Java and Neo4J - A Spring Boot application for tracking personal metrics.

## Prerequisites

- Java 21
- Maven
- Docker (for Neo4j database)

## Getting Started

### Running the Application

1. Start the Neo4j database using Docker:

```bash
docker-compose up -d
```

2. Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

Or on Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will be available at http://localhost:8080

## Testing

### Running Unit Tests

Run unit tests using the Maven Surefire plugin:

```bash
./mvnw test
```

This will execute all classes with names ending in `Test.java` but exclude those ending in `IT.java` or `IntegrationTest.java`.

### Running Integration Tests

Run integration tests using the Maven Failsafe plugin:

```bash
./mvnw failsafe:integration-test
```

This will execute all classes with names ending in `IT.java` or `IntegrationTest.java`.

### Running All Tests

To run both unit and integration tests:

```bash
./mvnw verify
```

## Project Structure

- `src/main/java` - Application source code
- `src/main/resources` - Configuration files and static resources
- `src/test/java` - Test source code
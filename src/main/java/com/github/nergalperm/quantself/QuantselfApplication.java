package com.github.nergalperm.quantself;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.neo4j.graphdb.GraphDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuantselfApplication {
    private final static Logger log = LoggerFactory.getLogger(QuantselfApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(QuantselfApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TempPersonRepository personRepository) {
        return (args) -> {

            personRepository.deleteAll();

            Person greg = new Person("Greg");
            Person roy = new Person("Roy");
            Person craig = new Person("Craig");

            List<Person> team = Arrays.asList(greg, roy, craig);

            log.info("Before linking up with Neo4j...");

            team.forEach(person -> log.info("\t" + person.toString()));

            personRepository.save(greg);
            personRepository.save(roy);
            personRepository.save(craig);

            greg = personRepository.findByName(greg.getName());
            greg.worksWith(roy);
            greg.worksWith(craig);
            personRepository.save(greg);

            roy = personRepository.findByName(roy.getName());
            roy.worksWith(craig);
            // We already know that roy works with greg
            personRepository.save(roy);

            // We already know craig works with roy and greg

            log.info("Lookup each person by name...");
            team.forEach(person -> log.info("\t" + personRepository.findByName(person.getName()).toString()));

            List<Person> teammates = personRepository.findByTeammatesName(greg.getName());
            log.info("The following have Greg as a teammate...");
            teammates.forEach(person -> log.info("\t" + person.getName()));
        };
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        final DatabaseManagementService managementService =
            new DatabaseManagementServiceBuilder(Path.of("neo4j")).build();
        final GraphDatabaseService database = managementService.database("neo4j");
        registerShutdownHook(managementService);
        return database;
    }

    private static void registerShutdownHook(DatabaseManagementService managementService) {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook(new Thread(managementService::shutdown));
    }

}
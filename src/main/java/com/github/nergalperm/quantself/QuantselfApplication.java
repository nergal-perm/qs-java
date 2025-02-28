package com.github.nergalperm.quantself;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuantselfApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuantselfApplication.class, args);
    }

    @Bean
    public Driver graphDatabaseService() {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687");
        registerShutdownHook(driver);
        return driver;
    }

    private static void registerShutdownHook(Driver driver) {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook(new Thread(driver::close));
    }
}
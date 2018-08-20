package com.es.datamigration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(basePackages = {"com.es.datamigration"})
@SpringBootApplication
@ImportResource("classpath:META-INF/spring/*.xml")
public class DataMigrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataMigrationApplication.class, args);
    }

}

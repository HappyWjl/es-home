package com.es.datasearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(basePackages = {"com.es.datasearch"})
@SpringBootApplication
@ImportResource("classpath:META-INF/spring/*.xml")
public class DataSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSearchApplication.class, args);
    }

}

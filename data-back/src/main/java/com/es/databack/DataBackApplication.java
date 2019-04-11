package com.es.databack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:META-INF/spring/*.xml")
@SpringBootApplication
public class DataBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataBackApplication.class, args);
    }

}

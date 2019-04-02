package com.es.datadump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan
@ImportResource("classpath:META-INF/spring/*.xml")
@Order(1)
public class DataDumpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataDumpApplication.class, args);
    }

}

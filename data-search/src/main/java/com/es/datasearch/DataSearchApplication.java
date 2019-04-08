package com.es.datasearch;

import com.es.stone.manager.ElasticSearchDumpManager;
import com.es.stone.manager.ElasticSearchInitClientManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.es.datasearch"})
@SpringBootApplication
public class DataSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSearchApplication.class, args);
    }

    @Bean
    public ElasticSearchDumpManager getElasticSearchDumpManager() {
        return new ElasticSearchDumpManager();
    }

    @Bean
    public ElasticSearchInitClientManager getElasticSearchInitClientManager() {
        return new ElasticSearchInitClientManager();
    }

}

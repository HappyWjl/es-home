package com.es.datamigration;

import com.es.stone.manager.ElasticSearchDumpManager;
import com.es.stone.manager.ElasticSearchIndexManager;
import com.es.stone.manager.ElasticSearchInitClientManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(basePackages = {"com.es.datamigration"})
@SpringBootApplication
@ImportResource("classpath:META-INF/spring/*.xml")
public class DataMigrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataMigrationApplication.class, args);
    }

    @Bean
    public ElasticSearchDumpManager getElasticSearchDumpManager() {
        return new ElasticSearchDumpManager();
    }

    @Bean
    public ElasticSearchInitClientManager getElasticSearchInitClientManager() {
        return new ElasticSearchInitClientManager();
    }

    @Bean
    public ElasticSearchIndexManager getElasticSearchIndexManager() {
        return new ElasticSearchIndexManager();
    }

}

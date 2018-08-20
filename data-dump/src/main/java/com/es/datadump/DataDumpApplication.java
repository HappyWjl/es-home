package com.es.datadump;

import com.es.stone.manager.ElasticSearchInitClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan
@ImportResource("classpath:META-INF/spring/*.xml")
@Order(1)
public class DataDumpApplication {

    private final static Logger logger = LoggerFactory.getLogger(DataDumpApplication.class);

    @Autowired
    private ElasticSearchInitClientManager elasticSearchClientManager;

    public static void main(String[] args) {
        SpringApplication.run(DataDumpApplication.class, args);
    }

    public void run(String... args) throws Exception {
        String ok = "the service server started!";
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
                + ok);
        elasticSearchClientManager.getElasticClient();
    }

}

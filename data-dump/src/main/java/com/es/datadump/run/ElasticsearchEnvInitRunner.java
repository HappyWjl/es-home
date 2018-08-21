package com.es.datadump.run;

import com.es.datadump.manager.elasticsearch.ElasticSearchIndexManager;
import com.es.stone.manager.ElasticSearchDumpManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 此处只能监听到ContextClosedEvent
 * 因此，考虑使用CommandLineRunner实现启动后自动执行
 * 同步模块启动校验特殊索引配置，在此类中添加。
 *
 * @author Administrator
 */
@Component
@Order(2)
public class ElasticsearchEnvInitRunner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(ElasticsearchEnvInitRunner.class);

    @Autowired
    private ElasticSearchIndexManager elasticSearchIndexManager;

    @Override
    public void run(String... args) throws Exception {
        logger.info("-------------------------------------检查ES索引状态---------------------------------------------");
        boolean flag = elasticSearchIndexManager.checkIndex("db_search.tb_article");//特殊索引配置入口,可直接追加
        if (!flag) {
            System.exit(0);
        }
        logger.info("-------------------------------------ES索引状态检查完成---------------------------------------------");
    }

}

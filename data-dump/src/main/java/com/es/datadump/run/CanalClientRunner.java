package com.es.datadump.run;

import com.alibaba.otter.canal.client.CanalConnector;
import com.es.datadump.manager.canal.CanalCoreManager;
import com.es.datadump.manager.canal.CanalInitClientManager;
import com.es.datadump.manager.ServiceImportManager;
import com.es.stone.manager.ElasticSearchDumpManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 此处只能监听到ContextClosedEvent
 * 因此，考虑使用CommandLineRunner实现启动后自动执行
 *
 * @author Administrator
 */
@Slf4j
@Component
//@Order(3)
public class CanalClientRunner implements CommandLineRunner {

    @Autowired
    private CanalInitClientManager canalInitClientManager;

    @Autowired
    private ElasticSearchDumpManager elasticSearchDumpManager;

    @Autowired
    private ServiceImportManager serviceImportManager;

    @Override
    public void run(String... args) {
        log.info("-------------------------------------canal服务启动----------------------------------------------");
        // 根据ip，直接创建链接，无HA的功能
        CanalConnector canalConnector = canalInitClientManager.getCanalConnector();

        final CanalCoreManager canalManager = new CanalCoreManager(canalInitClientManager.getDestination());
        canalManager.setCanalConnector(canalConnector);
        canalManager.setElasticSearchDumpManager(elasticSearchDumpManager);
        canalManager.setServiceImportManager(serviceImportManager);
        canalManager.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {
                try {
                    log.info("## stop the canal client");
                    canalManager.stop();
                } catch (Throwable e) {
                    log.warn("##something goes wrong when stopping canal:", e);
                } finally {
                    log.info("## canal client is down.");
                }
            }

        });
    }

}

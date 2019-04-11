package com.es.datamigration.run;

import com.es.stone.manager.ElasticSearchInitClientManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 此处只能监听到ContextClosedEvent
 * 因此，考虑使用CommandLineRunner实现启动后自动执行
 * 
 * @author Administrator
 *
 */
@Slf4j
@Component
//@Order(3)
public class ElasticSearchInitRunner implements CommandLineRunner {

	@Autowired
	private ElasticSearchInitClientManager elasticSearchInitClientManager;
	
	@Override
	public void run(String... args) {
		log.info("-----------------------------ES连接池初始化------------------------------------");
		elasticSearchInitClientManager.initClientPool();
		log.info("-----------------------------ES连接池初始化完成------------------------------------");
	}

}

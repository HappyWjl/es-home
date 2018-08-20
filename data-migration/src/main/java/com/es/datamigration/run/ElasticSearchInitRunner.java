package com.es.datamigration.run;

import com.es.stone.manager.ElasticSearchInitClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Component
//@Order(3)
public class ElasticSearchInitRunner implements CommandLineRunner {
	private final static Logger logger = LoggerFactory.getLogger(ElasticSearchInitRunner.class);
	@Autowired
	private ElasticSearchInitClientManager elasticSearchInitClientManager;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("-----------------------------ES连接池初始化------------------------------------");
		elasticSearchInitClientManager.initClientPool();
		logger.info("-----------------------------ES连接池初始化完成------------------------------------");
	}

}

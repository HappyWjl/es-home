
package com.es.datamigration.controller;

import com.es.datamigration.manager.elasticsearch.ElasticSearchIndexManager;
import com.es.datamigration.manager.article.ArticleSyncToEsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章搜索相关
 *
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleSyncToEsManager articleSyncToEsManager;

    @Autowired
    private ElasticSearchIndexManager elasticSearchIndexManager;

    /**
     * 开启同步数据，将article表中数据同步到ES
     * @return
     */
    @GetMapping("/articletoes")
    public String syncPlaceToEs() {
    	String result = articleSyncToEsManager.syncDataControl();
        return result;
    }
    
    /**
     * 查看同步进度
     * @return
     */
    @RequestMapping("/createindex")
    public boolean createIndex() {
    	logger.info("-------------------------------------检查ES索引状态---------------------------------------------");
		boolean flag = elasticSearchIndexManager.checkIndex("db_search.tb_article");//特殊索引配置入口,可直接追加
		logger.info("-------------------------------------ES索引状态检查完成---------------------------------------------");
        return flag;
    }

}

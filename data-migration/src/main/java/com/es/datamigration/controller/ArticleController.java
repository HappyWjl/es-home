
package com.es.datamigration.controller;

import com.es.stone.manager.ElasticSearchIndexManager;
import com.es.datamigration.manager.article.ArticleSyncToEsManager;
import com.es.stone.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章搜索相关
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleSyncToEsManager articleSyncToEsManager;

    @Autowired
    private ElasticSearchIndexManager elasticSearchIndexManager;

    /**
     * 开启同步数据，将article表中数据同步到ES
     * @return
     */
    @GetMapping("/articleToEs")
    public String syncPlaceToEs() {
        return articleSyncToEsManager.syncDataControl();
    }
    
    /**
     * 检查ES索引状态
     * @return
     */
    @RequestMapping("/checkIndex")
    public boolean createIndex() {
        boolean flag = Boolean.FALSE;
        try {
            log.info("-------------------------------------检查ES索引状态---------------------------------------------");
            flag = elasticSearchIndexManager.checkIndex(EsConstant.EsIndexName.DB_SEARCH_TB_ARTICLE);//特殊索引配置入口,可直接追加
            log.info("-------------------------------------ES索引状态检查完成---------------------------------------------");

        } catch (Exception e) {
            log.info("checkIndex is error!", e);
        }
        return flag;
    }

}

package com.es.datadump.manager;

import com.alibaba.fastjson.JSON;
import com.es.datadump.manager.article.ArticleCollectManager;
import com.es.stone.manager.ElasticSearchDumpManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ServiceImportManager {

    private final static Logger logger = LoggerFactory.getLogger(ServiceImportManager.class);

    @Autowired
    private ElasticSearchDumpManager elasticSearchDumpManager;

    /**
     * 区分索引进行业务关联同步
     *
     * @param colMap
     * @param index
     * @return
     */
    public Map getDateMap(Map colMap, String index) {
        if ("place.tb_place".equals(index)) {
            placeListInfoDump(colMap, index);
        } else {
            elasticSearchDumpManager.insertOrUpdateToEs(colMap, index);
        }
        logger.debug("------------------ colMap:{}, index:{}", JSON.toJSONString(colMap), index);
        return colMap;
    }

    /**
     * 根据place表同步数据
     *
     * @param colMap
     * @param index
     */
    protected void placeListInfoDump(Map colMap, String index) {

        logger.info("ServiceImportManager.placeListInfoDump colMap:{}", JSON.toJSONString(colMap));

        elasticSearchDumpManager.insertOrUpdateToEs(colMap, index + "_search");
    }

}

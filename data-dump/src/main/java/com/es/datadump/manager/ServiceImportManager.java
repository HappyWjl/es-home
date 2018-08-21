package com.es.datadump.manager;

import com.alibaba.fastjson.JSON;
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
        if ("db_search.tb_ts".equals(index)) {
            //判断是哪种特殊索引，根据需求，处理数据，再 存入/更新 到es
            txListInfoDump(colMap, index);
        } else {
            elasticSearchDumpManager.insertOrUpdateToEs(colMap, index);
        }
        logger.debug("------------------ colMap:{}, index:{}", JSON.toJSONString(colMap), index);
        return colMap;
    }

    /**
     * 根据特殊业务表同步数据
     *
     * @param colMap
     * @param index
     */
    protected void txListInfoDump(Map colMap, String index) {
        logger.info("ServiceImportManager.txListInfoDump colMap:{}", JSON.toJSONString(colMap));
        //此处可进行特殊逻辑处理，根据需求来
        elasticSearchDumpManager.insertOrUpdateToEs(colMap, index);
    }

}

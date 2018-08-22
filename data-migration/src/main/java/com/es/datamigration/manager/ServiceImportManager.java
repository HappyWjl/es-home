package com.es.datamigration.manager;

import com.alibaba.fastjson.JSON;
import com.es.datamigration.converter.LocationConverter;
import com.es.stone.manager.ElasticSearchDumpManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ServiceImportManager {

    @Autowired
    private ElasticSearchDumpManager elasticSearchDumpManager;

    private final static Logger logger = LoggerFactory.getLogger(ServiceImportManager.class);

    /**
     * 区分索引进行业务关联同步
     *
     * @param colMap
     * @param index
     * @return
     * @throws Exception
     */
    public Map getDateMap(Map colMap, String index) throws Exception {
        if ("db_search.tb_ts".equals(index)) {
            //判断是哪种特殊索引，根据需求，处理数据，再 存入/更新 到es
            txListInfoDump(colMap, index);
        } else if ("db_search.tb_article".equals(index)) {
            //判断是否是article索引，根据需求，处理数据，再 存入/更新 到es
            articleListInfoDump(colMap, index);
        } else {
            elasticSearchDumpManager.insertOrUpdateToEs(colMap, index);
        }
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

    /**
     * 根据article表同步数据
     *
     * @param colMap
     * @param index
     */
    protected void articleListInfoDump(Map colMap, String index) {
        //经纬度合并
        LocationConverter.locationConverter(colMap);
        elasticSearchDumpManager.insertOrUpdateToEs(colMap, index);
    }

}

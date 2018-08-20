package com.es.datamigration.manager;

import com.alibaba.fastjson.JSON;
import com.es.datamigration.converter.LatAndlngPlaceConverter;
import com.es.datamigration.manager.article.ArticleCollectManager;
import com.es.stone.manager.ElasticSearchDumpManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ServiceImportManager {

    @Autowired
    private ElasticSearchDumpManager elasticSearchDumpManager;

    @Autowired
    private ArticleCollectManager userCollectManager;

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
        if ("article.tb_place".equals(index)) {
            placeListInfoDump(colMap, index);
        } else if ("outplace.tb_place".equals(index)){
            outPlaceListInfoDump(colMap, index);
        } else {
            elasticSearchDumpManager.insertOrUpdateToEs(colMap, index);
        }
        return colMap;
    }

    /**
     * 很据黄页场馆更新数据
     * @param colMap
     * @param index
     */
    private void outPlaceListInfoDump(Map colMap, String index) {
        //经纬度合并
        LatAndlngPlaceConverter.latAndlonConvert(colMap);

        Map userCollect = userCollectManager.getUserCollectByPlaceId(Long.parseLong(colMap.get("id").toString()));

        colMap.put("userCollect", userCollect);//tb_user_collect字段
        colMap.put("key_words", (colMap.get("name") == null ? "":colMap.get("name").toString()) +
                (colMap.get("code") == null ? "":colMap.get("code").toString()) +
                (colMap.get("address") == null ? "":colMap.get("address").toString()));//拼接name + code + address字段
        if(colMap.get("business_mode") != null && Integer.parseInt(colMap.get("business_mode").toString()) >= 100){
            colMap.put("source_type", 0);//tb_place表，黄页场馆，为0

            logger.info("outPlaceListInfoDump:{}", JSON.toJSONString(colMap));

            elasticSearchDumpManager.insertOrUpdateToEs(colMap, "article.tb_place" + "_search");
        } else{
            logger.info("outPlaceListInfoDump business_mode <100 :{}", JSON.toJSONString(colMap));
        }
    }

    /**
     * 根据place表同步数据
     *
     * @param colMap
     * @param index
     */
    protected void placeListInfoDump(Map colMap, String index) {

        //经纬度合并
        LatAndlngPlaceConverter.latAndlonConvert(colMap);

        elasticSearchDumpManager.insertOrUpdateToEs(colMap, index + "_search");
    }

}

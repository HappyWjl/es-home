package com.es.datamigration.util;

import com.alibaba.fastjson.JSON;
import com.es.datamigration.model.TbArticleDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ConvertUserUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConvertUserUtil.class);

    public static Map convertToMap(TbArticleDO tbArticleDO) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", tbArticleDO.getId());
        map.put("title", tbArticleDO.getTitle());
        map.put("content", tbArticleDO.getContent());
        map.put("state", tbArticleDO.getState());
        map.put("latitude", tbArticleDO.getLatitude());
        map.put("longitude", tbArticleDO.getLongitude());
        map.put("create_time", tbArticleDO.getCreateTime());
        map.put("update_time", tbArticleDO.getUpdateTime());
        LOGGER.info("TbArticleDO.convertToMap:{}", JSON.toJSONString(map));
        return map;
    }

}

package com.es.datamigration.util;

import com.alibaba.fastjson.JSON;
import com.es.datamigration.model.TbArticleDO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ConvertMapUtil {

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
        log.info("TbArticleDO.convertToMap:{}", JSON.toJSONString(map));
        return map;
    }

}

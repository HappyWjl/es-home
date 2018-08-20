package com.es.datamigration.converter;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 获取经纬度，返回经纬度字符串
 *
 * @author Administrator
 */
public class LatAndlngPlaceConverter {
    public static void latAndlonConvert(Map colMap) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(colMap.get("lat").toString()) && StringUtils.isNotBlank(colMap.get("lng").toString())) {
            sb.append(colMap.get("lat").toString()).append(',').append(colMap.get("lng").toString());
            colMap.put("location", sb.toString());
            colMap.put("place_lat", colMap.get("lat").toString());
            colMap.put("place_lng", colMap.get("lng").toString());
            colMap.remove("lat");
            colMap.remove("lng");
        }
    }
}

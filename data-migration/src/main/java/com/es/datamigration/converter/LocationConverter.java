package com.es.datamigration.converter;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 获取经纬度，返回经纬度字符串
 *
 * @author Administrator
 */
public class LocationConverter {

    public static void locationConverter(Map colMap) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(colMap.get("latitude").toString()) && StringUtils.isNotBlank(colMap.get("longitude").toString())) {
            sb.append(colMap.get("latitude").toString()).append(',').append(colMap.get("longitude").toString());
            colMap.put("location", sb.toString());
        }
    }

}

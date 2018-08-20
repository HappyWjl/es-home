package com.es.datadump.converter;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 获取经纬度，返回经纬度字符串
 *
 * @author Administrator
 */
public class LatAndlonConverter {

    public static void latAndlonConvert(Map colMap) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank((String) colMap.get("lat")) && StringUtils.isNotBlank((String) colMap.get("lng"))) {
            sb.append((String) colMap.get("lat")).append(',').append((String) colMap.get("lng"));
            colMap.put("location", sb.toString());
            colMap.remove("lat");
            colMap.remove("lng");
        }
    }

}

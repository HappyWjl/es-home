package com.es.stone.converter;

import com.es.stone.constant.EsConstant;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 获取经纬度，返回经纬度字符串
 *
 * @author Administrator
 */
public class LocationConverter {

    public static void locationConverter(Map colMap) {
        StringBuffer sb = new StringBuffer();
        if (!StringUtils.isEmpty(colMap.get(EsConstant.GEO.LATITUDE).toString()) &&
                !StringUtils.isEmpty(colMap.get(EsConstant.GEO.LONGITUDE).toString())) {
            sb.append(colMap.get(EsConstant.GEO.LATITUDE).toString())
                .append(',')
                .append(colMap.get(EsConstant.GEO.LONGITUDE).toString());
            colMap.put(EsConstant.GEO.LOCATION, sb.toString());
        }
    }

}

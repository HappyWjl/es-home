package com.es.stone.converter;

import com.es.stone.constant.EsConstant;

import java.util.Map;

/**
 * 将数据id拼接后缀
 *
 * @author Administrator
 */
public class IdConverter {

    public static void IdConverter(Map colMap, String index) {
        colMap.put(EsConstant.ES_KEY, colMap.get(EsConstant.ES_KEY).toString() + index);
    }

}

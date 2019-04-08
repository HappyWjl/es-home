package com.es.datasearch.util;

import com.es.stone.constant.EsConstant;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class ConvertDateUtil {

    private static SimpleDateFormat sdflocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static SimpleDateFormat sdflocalsimple = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static SimpleDateFormat sdfutc = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    static {
        sdflocal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        sdfutc.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static Map convertDate(Map mapDTO) throws ParseException {

        if (StringUtils.isNotBlank(mapDTO.get(EsConstant.SqlWord.CREATE_TIME) == null ? "" : (String) mapDTO.get(EsConstant.SqlWord.CREATE_TIME))) {
            mapDTO.put(EsConstant.SqlWord.CREATE_TIME, sdflocal.format(sdfutc.parse((String) mapDTO.get(EsConstant.SqlWord.CREATE_TIME))));
        }

        if (StringUtils.isNotBlank(mapDTO.get(EsConstant.SqlWord.UPDATE_TIME) == null ? "" : (String) mapDTO.get(EsConstant.SqlWord.UPDATE_TIME))) {
            mapDTO.put(EsConstant.SqlWord.UPDATE_TIME, sdflocal.format(sdfutc.parse((String) mapDTO.get(EsConstant.SqlWord.UPDATE_TIME))));
        }
        return mapDTO;
    }

}

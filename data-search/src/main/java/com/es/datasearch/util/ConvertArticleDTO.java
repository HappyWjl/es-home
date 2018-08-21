package com.es.datasearch.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class ConvertArticleDTO {

    private static SimpleDateFormat sdflocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static SimpleDateFormat sdflocalsimple = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static SimpleDateFormat sdfutc = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    static {
        sdflocal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        sdfutc.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static Map convertDate(Map mapDTO) throws ParseException {

        if (StringUtils.isNotBlank(mapDTO.get("create_time") == null ? "" : (String) mapDTO.get("create_time"))) {
            mapDTO.put("create_time", sdflocalsimple.format(sdfutc.parse((String) mapDTO.get("create_time"))));
        }

        if (StringUtils.isNotBlank(mapDTO.get("end_time") == null ? "" : (String) mapDTO.get("update_time"))) {
            mapDTO.put("update_time", sdflocal.format(sdfutc.parse((String) mapDTO.get("update_time"))));
        }
        return mapDTO;
    }

}

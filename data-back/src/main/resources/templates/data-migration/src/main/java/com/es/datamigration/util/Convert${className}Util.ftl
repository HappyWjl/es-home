package ${package}.util;

import java.util.HashMap;
import java.util.Map;

import ${package}.model.${className}DO;
import com.es.stone.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: [${table.tableDesc}转map]
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
@Slf4j
public class Convert${className}Util {

	public static Map convertToMap(${className}DO ${classNameLower}DO) {
        try {
            return ConvertUtil.objectToMap(${classNameLower}DO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap();
	}
	
}

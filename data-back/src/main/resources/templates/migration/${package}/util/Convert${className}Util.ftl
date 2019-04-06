package ${package}.service;

import java.util.List;
import java.util.Map;

import ${package}.model.Page;
import ${package}.model.${className}Model;
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
		return ConvertUtil.objectToMap(${classNameLower}DO);
	}
	
}

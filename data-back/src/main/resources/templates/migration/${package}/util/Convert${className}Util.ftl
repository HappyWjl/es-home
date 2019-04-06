package ${package}.service;

import java.util.List;

import ${package}.model.Page;
import ${package}.model.${className}Model;

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

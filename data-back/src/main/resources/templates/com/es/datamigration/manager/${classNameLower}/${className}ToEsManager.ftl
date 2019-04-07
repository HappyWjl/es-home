package ${package}.manager.${classNameLower};

import ${package}.manager.ServiceImportManager;
import ${package}.mapper.${className}DOMapper;
import ${package}.model.${className}DO;
import ${package}.util.Convert${className}Util;
import com.es.stone.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/** 
 * Description: [${table.tableDesc}同步数据到ES]
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
@Slf4j
public class ${className}ToEsManager {

	@Autowired
	private ServiceImportManager serviceImportManager;

	@Autowired
	private ${className}DOMapper ${classNameLower}DOMapper;

	/**
	* 同步数据到ES主控方法
	*/
	public String syncDataControl() {
		List<${className}DO> ${classNameLower}DOList = ${classNameLower}DOMapper.selectAll();

		for (${className}DO ${classNameLower}DO : ${classNameLower}DOList) {
			Map colMap = Convert${className}Util.convertToMap(${classNameLower}DO);
			colMap.put(EsConstant.ES_KEY, colMap.get(EsConstant.ID).toString());
			try {
				serviceImportManager.getDateMap(colMap, "${dbName}." + "${tableName}");
			} catch (Exception e) {
				log.error("同步数据异常，ID: {} , e: {}", ${classNameLower}DO.getId(), e);
			}
		}
		return "success";
	}

}

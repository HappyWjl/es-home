package ${package}.service.impl;

import ${package}.manager.${classNameLower}.${className}SearchManager;
import ${package}.param.Query${className}SearchVO;
import ${package}.result.ResultByEsDO;
import ${package}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Description: [${table.tableDesc}服务实现]
* Created on ${date}
* @author  <a href="mailto: ${email}">${author}</a>
* @version 1.0
* Copyright (c) ${year} ${website}
*/
@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements ${className}Service {

	@Autowired
	${className}SearchManager ${classNameLower}SearchManager;

	@Override
	public ResultByEsDO get${className}List(Query${className}SearchVO query${className}SearchVO) {
		return ${classNameLower}SearchManager.query${className}List(query${className}SearchVO);
	}

}

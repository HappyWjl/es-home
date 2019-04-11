package ${package}.service;

import ${package}.param.Query${className}SearchVO;
import ${package}.result.ResultByEsDO;

/**
 * Description: [${table.tableDesc}数据ES查询]
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
public interface ${className}Service {

	/**
	 * <p>Discription:[${table.tableDesc}数据ES查询]</p>
	 * Created on ${date}
	 *
	 * @author:${author}
	 */
	ResultByEsDO get${className}List (Query${className}SearchVO query${className}SearchVO);

}

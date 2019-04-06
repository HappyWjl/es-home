package ${package}.service;

import java.util.List;

import ${package}.model.Page;
import ${package}.model.${className}Model;

/**
 * Description: [${table.tableDesc}服务]
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
public interface ${className}Service {

	/**
	 * <p>Discription:[${table.tableDesc}数据分页查询]</p>
	 * Created on ${date}
	 * @param pager ${table.tableDesc}数据分页条件
	 * @param ${classNameLower}Model ${table.tableDesc}数据查询条件
	 * @param queryFields ${table.tableDesc}数据查询字段集合
	 * @return List<${className}Model>分页数据
	 *													       	 
	 * @author:${author}
	 */
	 public	List<${className}Model> queryPage${className}(Page page, ${className}Model ${classNameLower}Model, String queryFields);
	 
	 /**
	 * <p>Discription:[${table.tableDesc}数据不分页查询]</p>
	 * Created on ${date}
	 * @param pager ${table.tableDesc}数据分页条件
	 * @param ${classNameLower}Model ${table.tableDesc}数据查询条件
	 * @param queryFields ${table.tableDesc}数据查询字段集合
	 * @return List<${className}Model>分页数据
	 *													       	 
	 * @author:${author}
	 */
	 public	List<${className}Model> queryList${className}(${className}Model ${classNameLower}Model, String queryFields);
	
	/**
	 * <p>Discription:[${table.tableDesc}数据查询总条数]</p>
	 * Created on ${date}
	 * @param ${classNameLower}Model ${table.tableDesc}数据查询条件
	 * @return 查询条数	 
	 * @author:${author}
	 */
	public Long queryCount${className}(${className}Model ${classNameLower}Model);
	
	/**
	 * <p>Discription:[根据id查询${table.tableDesc}数据]</p>
	 * Created on ${date}
	 * @param id ${table.tableDesc}数据id
	 * @return ${className}Model 单条数据	 
	 * @author:${author}
	 */
	public ${className}Model query${className}ById(String id);

	/**
	 * <p>Discription:[${table.tableDesc}数据新增]</p>
	 * Created on ${date}
	 * @param ${classNameLower}Model ${table.tableDesc}数据
	 * @return String 添加成功的id
	 * @author:${author}
	 */
	public int save(${className}Model ${classNameLower}Model);
	
	/**
	 * <p>Discription:[${table.tableDesc}数据编辑]</p>
	 * Created on ${date}
	 * @param ${classNameLower}Model ${table.tableDesc}数据
	 * @return 成功条数 
	 * @author:${author}
	 */
	public int edit(${className}Model ${classNameLower}Model);
	
	/**
	 * <p>Discription:[${table.tableDesc}数据删除]</p>
	 * Created on ${date}
	 * @param id ${table.tableDesc}数据id
	 * @return 成功条数 	
	 * @author:${author}
	 */
	public int remove${className}ById(String id);
	
	/**
	 * <p>Discription:[${table.tableDesc}数据批量删除]</p>
	 * Created on ${date}
	 * @param ids ${table.tableDesc}数据id的集合
	 * @return 成功条数 
	 * @author:${author}
	 */
	public int remove${className}ByIds(String ids);
	
}

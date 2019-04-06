package ${package}.dao;

import ${package}.model.${className}Model;
import ${package}.model.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/** 
 * <p>Description: [${table.tableDesc}dao]</p>
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
public interface ${className}DOMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<${className}Model> queryPage${className}(@Param("page")Page page, @Param("entity")${className}Model entity, @Param("queryFields")List queryFields);

	/**
	* 查询固定参数
	*/
	List<${className}Model> queryList${className}(@Param("entity")${className}Model entity, @Param("queryFields")List queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount${className}(@Param("entity")${className}Model entity);
	
	/**
	* 查询单个实体
	*/
	${className}Model query${className}ById(@Param("id")String id,  @Param("queryFields")List queryFields);
	
	/**
	* 新增
	*/
	int add${className}(${className}Model entity);
	
	/**
	* 修改
	*/
	int update${className}(${className}Model entity);
	
	/**
	* 批量删除
	*/
	int remove${className}ByIds(List code);
	
	/**
	* 删除
	*/
	int remove${className}ById(@Param("id")String id);

}

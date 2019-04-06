package ${package}.mapper;

import ${package}.model.${className}DO;
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
	List<${className}DO> queryPage${className}(@Param("page")Page page, @Param("entity")${className}DO entity, @Param("queryFields")List queryFields);

	/**
	* 查询固定参数
	*/
	List<${className}DO> queryList${className}(@Param("entity")${className}DO entity, @Param("queryFields")List queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount${className}(@Param("entity")${className}DO entity);
	
	/**
	* 查询单个实体
	*/
	${className}DO query${className}ById(@Param("id")String id,  @Param("queryFields")List queryFields);
	
	/**
	* 新增
	*/
	int add${className}(${className}DO entity);
	
	/**
	* 修改
	*/
	int update${className}(${className}DO entity);
	
	/**
	* 批量删除
	*/
	int remove${className}ByIds(List code);
	
	/**
	* 删除
	*/
	int remove${className}ById(@Param("id")String id);

}

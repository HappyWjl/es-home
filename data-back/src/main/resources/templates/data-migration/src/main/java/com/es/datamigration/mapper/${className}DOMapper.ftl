package ${package}.mapper;

import ${package}.model.${className}DO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/** 
 * <p>Description: [${table.tableDesc}dao]</p>
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
public interface ${className}DOMapper{

	/**
	* 查询所有数据
	*/
	List<${className}DO> selectAll ();

    /**
    * 分页查询所有数据列表
    * @return
    */
    List<${className}DO> selectByPage(@Param("skip") int skip, @Param("limit") int limit);

}

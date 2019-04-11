package ${package}.model;

import java.io.Serializable;
/** 
 * <p>Description: [${table.tableDesc}model]</p>
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
public class ${className}Model  implements Serializable {

	private static final long serialVersionUID = 1L;

<#list table.columns as column>
	/**
     * ${column.label}
     */
	private ${column.type} ${column.name};
</#list>
	
	// setter and getter
<#list table.columns as column>
	/**
	* <p>Discription:[${column.label}]</p>
	* Created on ${date}
	* @return ${column.type}
    * @author:${author}
    */
	public ${column.type} get${column.nameUpper}(){
		return ${column.name};
	}
	/**
	* <p>Discription:[${column.label}]</p>
	* Created on ${date}
	* @author:${author}
	*/
	public void set${column.nameUpper}(${column.type} ${column.name}){
		this.${column.name} = ${column.name};
	}
</#list>
}

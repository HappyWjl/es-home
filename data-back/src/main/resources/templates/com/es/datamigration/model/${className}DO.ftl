package ${package}.model;

import lombok.Data;
import java.io.Serializable;
/** 
 * <p>Description: [${table.tableDesc}model]</p>
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
@Data
public class ${className}DO implements Serializable {

	private static final long serialVersionUID = 1L;
<#list table.columns as column>

	private ${column.type} ${column.name};
</#list>

}

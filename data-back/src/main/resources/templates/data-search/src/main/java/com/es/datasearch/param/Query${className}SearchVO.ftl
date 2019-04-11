package ${package}.param;

import java.io.Serializable;

/** 
 * <p>Description: [${table.tableDesc}搜索传入对象]</p>
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
public class Query${className}SearchVO implements Serializable {

    /**
    * 每页大小
    */
    public int pageSize;

    /**
    * 页码
    */
    public int pageNo;

    /**
    * 关键字,模糊查询
    */
    public String keyWords;

    /**
    * 排序规则，0：创建时间倒序；1：距离由近到远排序
    */
    public int sortType;

    /**
    * 传入经度
    */
    public double latitude;

    /**
    * 传入纬度
    */
    public double longitude;

    /**
    * 创建开始时间
    */
    public String createStartTime;

    /**
    * 创建结束时间
    */
    public String createEndTime;
<#list table.columns as column>

    /**
    * ${column.label}
    */
    public ${column.type} ${column.name};
</#list>

}

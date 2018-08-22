package com.es.datasearch.param;

import java.io.Serializable;

public class QueryArticleSearchVO implements Serializable {

    // 每页大小
    public int pageSize;

    // 页码
    public int pageNo;

    // 关键字,模糊查询
    public String keyWords;

    // 排序规则，0：创建时间倒序；1：距离由近到远排序
    public int sortType;

    // 传入经度
    public double latitude;

    // 传入纬度
    public double longitude;

    // 文章id
    public long id;

    // 文章标题
    public String title;

    // 文章内容
    public String content;

    // 文章状态，按照状态id + “，”分隔的字符串传入
    public String state;

    // 创建开始时间
    public String createStartTime;

    // 创建结束时间
    public String createEndTime;

}

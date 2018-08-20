package com.es.datasearch.param;

import java.io.Serializable;

public class QueryArticleSearchVO implements Serializable {

    // 每页大小
    public int pageSize;

    // 页码
    public int pageNo;

    // 关键字,模糊查询
    public String keyWords;

    // 文章id
    public long id;

    // 文章标题
    public String title;

    // 文章内容
    public String content;

    // 创建开始时间
    public String createStartTime;

    // 创建结束时间
    public String createEndTime;

}

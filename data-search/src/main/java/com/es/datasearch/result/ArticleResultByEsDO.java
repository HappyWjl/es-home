package com.es.datasearch.result;

import com.es.datasearch.model.TbArticleDO;

import java.util.List;

public class ArticleResultByEsDO {

    private int pageNo;

    private int pageSize;

    private List<TbArticleDO> articleList;

    private String esStatus;

    private int recordSize;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<TbArticleDO> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<TbArticleDO> articleList) {
        this.articleList = articleList;
    }

    public String getEsStatus() {
        return esStatus;
    }

    public void setEsStatus(String esStatus) {
        this.esStatus = esStatus;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

}

package com.es.stone.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Pager<T> implements Serializable {

    protected int pageSize;

    protected List<T> data;

    protected Integer totalCount;

    Pager() {
    }

    Pager(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Pager<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public Pager<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}

package com.es.stone.result;

import java.util.List;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public class BasePageResult<T> extends ResultSupport {
    private static final long serialVersionUID = 7378807577314788084L;
    protected int pageNo = 1;
    protected int pageSize;
    protected int totalCount;
    protected boolean hasNext;
    protected List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = 0;
        }
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return (pageNo - 1) * pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}

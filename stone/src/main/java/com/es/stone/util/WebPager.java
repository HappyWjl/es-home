package com.es.stone.util;

import java.util.List;

public class WebPager<T> extends Pager<T> {

    private static final long serialVersionUID = 320206316488698266L;

    private int currentPage;

    WebPager(int pageSize, int currentPage) {
        super(pageSize);
        this.currentPage = currentPage;
    }

    WebPager<T> load(List<T> data, int totalCount) {
        this.data = data;
        this.totalCount = totalCount;
        return this;
    }

    public int getTotalPage() {
        if (totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return totalCount / pageSize + 1;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }
}

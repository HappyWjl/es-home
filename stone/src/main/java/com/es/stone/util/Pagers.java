package com.es.stone.util;

import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public final class Pagers {

    private Pagers() {
    }

    public static <T> WebPager<T> buildWebPager(
            List<T> data, int pageSize, int currentPage, int totalCount) {
        return new WebPager<T>(pageSize, currentPage).load(data, totalCount);
    }

    public static <T> WebPager<T> emptyWebPager(int pageSize, int currentPage, int totalCount) {
        return buildWebPager(Collections.emptyList(), pageSize, currentPage, totalCount);
    }

    public static <T> MobilePager<T> buildMobilePager(
            List<T> data, Function<T, Long> anchorIdFunc, int pageSize, boolean getAnchorIdByLast) {
        return buildMobilePager(data, anchorIdFunc, pageSize, null, getAnchorIdByLast);
    }

    public static <T> MobilePager<T> buildMobilePager(
            List<T> data, Function<T, Long> anchorIdFunc, int pageSize, Integer totalCount, boolean getAnchorIdByLast) {
        return new MobilePager<T>(pageSize, totalCount).load(data, anchorIdFunc, getAnchorIdByLast);
    }

    public static <T> MobilePager<T> emptyMobilePager() {
        return buildMobilePager(Collections.emptyList(), null, 0, false);
    }

    public static <T> List<T> subList(List<T> data, int pageSize, int page) {
        int start = pageSize * (page - 1);
        if (start > data.size()) {
            return Collections.emptyList();
        }
        int end = pageSize * page;
        if (end > data.size()) {
            end = data.size();
        }
        return data.subList(start, end);
    }

    public static long validCursorId(long cursorId) {
        return cursorId < 0 ? 0 : cursorId;
    }

    public static int validPage(int page) {
        return page < 1 ? 1 : page;
    }

    public static int validPageSize(int pageSize) {
        Assert.isTrue(pageSize <= 200, "页数查询太多");
        return pageSize < 1 ? 20 : pageSize;
    }

}

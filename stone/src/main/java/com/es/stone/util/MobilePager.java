package com.es.stone.util;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;

public class MobilePager<T> extends Pager<T> {

    private static final long serialVersionUID = 4133617516309958598L;

    private Long nextAnchorId;

    private Boolean nextPage;

    MobilePager(int pageSize, Integer totalCount) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    /**
     * 这里不再对 data 排序（不知道排序规则，也可能会造成额外的性能浪费），调用方负责保证data正确的顺序
     *
     * @param data data
     * @param anchorIdFunc 锚点id的获取规则
     * @param getAnchorIdByLast 是否根据最后一个对象获取锚点id
     * @return pager
     */
    MobilePager<T> load(List<T> data, Function<T, Long> anchorIdFunc, boolean getAnchorIdByLast) {
        this.data = data;
        if (CollectionUtils.isEmpty(data)) {
            // nextPage 为 false 了，nextAnchorId 不再需要（因为不需要再获取更多内容了）
            this.nextPage = false;
            return this;
        }

        this.nextPage = data.size() == this.pageSize;
        if (!this.nextPage) {
            // nextPage 为 false 了，nextAnchorId 不再需要（因为不需要再获取更多内容了）
            return this;
        }

        T anchorItem;
        if (getAnchorIdByLast) {
            anchorItem = data.get(data.size() - 1);
        } else {
            anchorItem = data.get(0);
        }
        this.nextAnchorId = anchorIdFunc.apply(anchorItem);

        return this;
    }

    public Long getNextAnchorId() {
        return nextAnchorId;
    }

    public MobilePager<T> setNextAnchorId(Long nextAnchorId) {
        this.nextAnchorId = nextAnchorId;
        return this;
    }

    public Boolean getNextPage() {
        return nextPage;
    }

    public MobilePager<T> setNextPage(Boolean nextPage) {
        this.nextPage = nextPage;
        return this;
    }
}

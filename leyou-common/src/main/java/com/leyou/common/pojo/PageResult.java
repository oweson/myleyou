package com.leyou.common.pojo;

import java.util.List;

/**
 * @param <T> 分页通用类
 * @author li
 */
public class PageResult<T> {

    /**
     * 1 总条数
     */
    private Long total;
    /**
     * 2 总页数
     */
    private Long totalPage;
    /**
     * 3 当前页数据
     */
    private List<T> items;

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}
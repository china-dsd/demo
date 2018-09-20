package com.casic.oarp.datavisual.model;

import java.io.Serializable;

public class PageResult<T> implements Serializable {

    protected T rows;

    protected Integer page;

    protected Long total;

    public PageResult() {
    }

    public PageResult(T rows, Integer page, Long total) {
        this.rows = rows;
        this.page = page;
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "rows=" + rows +
                ", page=" + page +
                ", total=" + total +
                '}';
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

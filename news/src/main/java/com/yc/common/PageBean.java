package com.yc.common;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回工具类
 *
 * @param <T> 分页返回封装
 */
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = -4510052016725322882L;
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private Integer totalCount;
    private List<T> items;

    public PageBean() {
        super();
    }

    public PageBean(Integer pageNo, Integer pageSize, Integer totalCount) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
package com.kingstar.common;

import java.util.List;

/**
 * @Description:
 * @Author: myl
 * @Date: 2020/6/11 17:25
 */
public class PageResponseData<T> {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页长
     */
    private Integer pageSize;

    /**
     * 总记录
     */
    private Long totalNum;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 数据信息
     */
    private List<T> data;

    public PageResponseData(Long totalNum, List<T> data) {
        this.totalNum = totalNum;
        this.data = data;
    }

    public PageResponseData(Long totalNum, Integer totalPages, List<T> data) {
        this.totalNum = totalNum;
        this.totalPages = totalPages;
        this.data = data;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageResponseData{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalNum=" + totalNum +
                ", totalPages=" + totalPages +
                ", data=" + data +
                '}';
    }
}

package com.kingstar.common;

/**
 * @Description:请求参数对象（分页时用到）
 * @Author: myl
 * @Date: 2020/6/11 15:02
 */
public class ParamItem {

    /**
     * 页码
     */
    private Integer page;

    /**
     * 页长
     */
    private Integer length;

    public ParamItem() {
        this.page = 1;
        this.length = 10;
    }

    public ParamItem(Integer page, Integer length) {
        this.page = page > 1000 ? 1000 : page;
        this.length = length > 100 ? 100 : length;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if (page == null) {
            page = 1;
        }
        this.page = page;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        if (length == null) {
            length = 10;
        }
        if (length > 100) {
            length = 100;
        }
        this.length = length;
    }

}

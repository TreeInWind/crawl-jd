package com.kingstar.service;

import com.kingstar.common.ParamItem;
import com.kingstar.model.entity.Item;

import java.util.List;

/**
 * @Description:与数据库相关的业务类接口
 * @Author: myl
 * @Date: 2020/6/22 22:45
 */
public interface ItemService {
    /**
     * 统计所有的item数目
     *
     * @author yongliang
     * @param
     * @return long
     */
    long queryTotalItem();

    /**
     * 分页查询item列表
     *
     * @author yongliang
     * @param title
     * @param paramItem
     * @return java.util.List<com.kingstar.model.entity.Item>
     */
    List<Item> querySelectiveItemList(String title, ParamItem paramItem);


    /**
     * 获取所有分类名称
     *
     * @author yongliang
     * @param
     * @return java.util.List<java.lang.String>
     */
    List<String> getAllCategoryName();

    /**
     * 批量插入item信息
     *
     * @author yongliang
     * @param list
     * @return void
     */
    void saveBatchItems(List<Item> list);

    /**
     * 批量插入种类名称
     *
     * @author yongliang
     * @param list
     * @return void
     */
    void saveBatchCategory(List<String> list);


}

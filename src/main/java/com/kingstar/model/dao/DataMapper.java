package com.kingstar.model.dao;

import com.kingstar.model.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DataMapper {

    /**
     * 批量插入item
     *
     * @author yongliang
     * @param list
     * @return int
     */
    int insertBatchItems(List<Item> list);

    /**
     * 批量插入种类名称
     *
     * @author yongliang
     * @param list
     * @return int
     */
    int insertBatchCategory(List<String> list);

    /**
     * 获取所有分类名称
     *
     * @author yongliang
     * @param
     * @return java.util.List<java.lang.String>
     */
    List<String> getAllCategoryName();

    /**
     * 清空分类列表
     *
     * @author yongliang
     * @param
     * @return int
     */
    int clearCategoryList();

    /**
     * 分页查询item列表
     *
     * @author yongliang
     * @param title
     * @param start
     * @param pageLength
     * @return java.util.List<com.kingstar.model.entity.Item>
     */
    List<Item> querySelectiveItemList(@Param("title") String title, Integer start, Integer pageLength);


    /**
     * 统计item表中所有记录数
     *
     * @author yongliang
     * @param
     * @return long
     */
    long queryTotalItem();
}
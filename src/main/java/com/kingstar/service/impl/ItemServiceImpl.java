package com.kingstar.service.impl;

import com.kingstar.common.ParamItem;
import com.kingstar.model.dao.DataMapper;
import com.kingstar.model.entity.Item;
import com.kingstar.service.ItemService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: myl
 * @Date: 2020/6/22 22:46
 */
@Service
class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private DataMapper dataMapper;

    @Override
    public long queryTotalItem() {
        logger.info("正在统计当前item表中数据");
        return dataMapper.queryTotalItem();
    }

    @Override
    public List<Item> querySelectiveItemList(String title, ParamItem paramItem) {
        logger.info("正在分页查询item列表信息，参数title为"+title+",分页信息为"+ ToStringBuilder.reflectionToString(paramItem, ToStringStyle.DEFAULT_STYLE));
        int start = (paramItem.getPage()-1)*paramItem.getLength();
        return dataMapper.querySelectiveItemList(title,start,paramItem.getLength());
    }

    @Override
    public List<String> getAllCategoryName() {
        logger.info("正在查询所有分类信息");
        return dataMapper.getAllCategoryName();
    }

    @Override
    public void saveBatchItems(List<Item> list) {
        logger.info("批量保存item列表");
        if (list.isEmpty()) {
            return;
        }
        dataMapper.insertBatchItems(list);
    }

    @Override
    public void saveBatchCategory(List<String> list) {
        logger.info("批量保存分类列表");
        if (list.isEmpty()) {
            return;
        }
        dataMapper.insertBatchCategory(list);
    }
}

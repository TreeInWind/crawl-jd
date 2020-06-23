package com.kingstar.controller;

import com.kingstar.common.*;
import com.kingstar.handler.CrawlDispatcher;
import com.kingstar.model.entity.Item;
import com.kingstar.service.CrawlService;
import com.kingstar.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:爬取数据类控制器
 * @Author: myl
 * @Date: 2020/6/22 11:37
 */
@Api(tags = "爬取数据类请求接口")
@ApiModel
@RestController
@RequestMapping(value = "crawl")
public class CrawlJDDataController {

    @Autowired
    private CrawlService crawlService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CrawlDispatcher crawlDispatcher;

    @ApiOperation("爬取数据")
    @PostMapping(value = "")
    public ResponseData crawlData() {
        // 完整链接示例：https://search.jd.com/Search?keyword=碎纸机&wq=碎纸机&page=108&s=2676&click=0&scrolling=y
        List<String> keywordsList = itemService.getAllCategoryName();
        // 拼接结果示例："https://search.jd.com/Search?keyword=手机&wq=手机&page=";
        // 映射为初始链接
        List<String> spitResultList = keywordsList.stream().map(keywords -> Constants.ORIGIN_URL + keywords + "&wq=" + keywords + Constants.PAGE_PARAM).collect(Collectors.toList());
        crawlDispatcher.dispatchThreadToCrawl(spitResultList);
        return ResponseData.ok("查询中，可调用统计item表中总数据记录条数查看所爬取结果");
    }

    @ApiOperation("更新分类列表")
    @PutMapping(value = "")
    public ResponseData updateCategory() throws IOException {
        //  总分类的解析地址
        String url = "https://www.jd.com/allSort.aspx";
        System.out.println("更新种类数据"+url);
        crawlService.crawlCategoryData(url);
        return ResponseData.ok();
    }

    @ApiOperation("根据条件分页查询item列表")
    @GetMapping(value = "")
    public ResponseData querySelectiveStudentList(String title, ParamItem paramItem) {
        if (StringUtils.isEmpty(title) || StringUtils.equals("null",title)){
            return ResponseData.error(CodeEnum.E_400,"title不合法");
        }
        if (paramItem.getPage()<=0){
            return ResponseData.error(CodeEnum.E_400,"page不合法");
        }
        if (paramItem.getLength()<0){
            return ResponseData.error(CodeEnum.E_400,"length不合法");
        }
        List<Item> list = itemService.querySelectiveItemList(title, paramItem);
//        new PageResponseData<Item>()
        return ResponseData.ok(itemService.querySelectiveItemList(title, paramItem));
    }

    @ApiOperation("统计当前item表中总数据条数")
    @GetMapping(value = "/totalCount")
    public ResponseData queryTotalItem() {
        return ResponseData.ok(itemService.queryTotalItem());
    }

}

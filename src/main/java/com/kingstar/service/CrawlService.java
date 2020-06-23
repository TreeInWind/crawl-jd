package com.kingstar.service;

import com.kingstar.model.entity.Item;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * @Description:爬虫服务接口
 * @Author: myl
 * @Date: 2020/6/17 14:25
 */
public interface CrawlService {

    /**
     * 解析所爬取的HTML页面
     *
     * @author yongliang
     * @param html
     * @return java.util.List<com.kingstar.model.entity.Item>
     */
    List<Item> parseItemHtml(String html) throws IOException;


    /**
     * 获取某商品下前十条评论中的所有用户以及图片item列表
     *
     * @author yongliang
     * @param url
     * @param itemName
     * @return java.util.List<com.kingstar.model.entity.Item>
     */
    List<Item> getCommentImg(String url,String itemName) throws IOException ;

    /**
     * 获取解析得到的document下所有的超链接item列表
     *
     * @author yongliang
     * @param document
     * @return java.util.List<com.kingstar.model.entity.Item>
     */
    List<Item> getHyperlinkList(Document document);

    /**
     * 爬取数据
     *
     * @author yongliang
     * @param baseUrl
     * @return void
     */
    void crawlItemData(String baseUrl) throws IOException;


    /**
     * 爬取京东所有分类数据
     *
     * @author yongliang
     * @param url
     * @return java.util.List<java.lang.String>
     */
    void crawlCategoryData(String url) throws IOException;







}

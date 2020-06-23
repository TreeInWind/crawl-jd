package com.kingstar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kingstar.common.BusinessException;
import com.kingstar.common.Constants;
import com.kingstar.model.dao.DataMapper;
import com.kingstar.model.entity.Item;
import com.kingstar.redis.RedisService;
import com.kingstar.service.CrawlService;
import com.kingstar.service.ItemService;
import com.kingstar.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kingstar.utils.HttpUtils.doGetHtml;

/**
 * @Description:爬取服务接口实现
 * @Author: myl
 * @Date: 2020/6/17 14:29
 */
@Service
public class CrawlServiceImpl implements CrawlService {

    private final Logger logger = LoggerFactory.getLogger(CrawlServiceImpl.class);

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private RedisService redisService;

    @Override
    public List<Item> parseItemHtml(String html) throws IOException {
        logger.info("正在解析所爬取的HTML页面");
        Document dom = Jsoup.parse(html);
        // 获取当前页面下的商品列表
        Elements goodsElements = dom.select("div#J_main>div>div>li");
        List<Item> itemList = new ArrayList<>();
        for (Element goodsEle : goodsElements) {
            //sku
            String skuValue = goodsEle.attr("data-sku");
            String itemDetailUrl = "https://item.jd.com/" + skuValue + ".html";
            // 处理商品链接
            if (redisService.checkHaveSameUrl(itemDetailUrl)) {
                continue;
            }
            Elements itemNameEles = goodsEle.select("div[class=p-name p-name-type-2]");
            if (itemNameEles.isEmpty()) {
                itemNameEles = goodsEle.select("div[class=p-name]");
            }
            String itemName = itemNameEles.first().select("em").text();
            redisService.saveUrlToRedis(itemDetailUrl);
            itemList.add(new Item(itemName, itemDetailUrl));
            // 若商品sku值不同，则其商品链接、评论链接、加入购物车链接必然不同
            // 处理评论链接
            String commentUrl = itemDetailUrl + "#comment";
            redisService.saveUrlToRedis(commentUrl);
            itemList.add(new Item(itemName + "-评论", commentUrl));
            // 处理加入购物车连接
            String addCartUrl = "https://cart.jd.com/gate.action?pid=" + skuValue + "&pcount=1&ptype=1";
            redisService.saveUrlToRedis(addCartUrl);
            itemList.add(new Item(itemName + "-加入购物车", commentUrl));

            // TODO 出于公司网络对www.jd.com/*的屏蔽，以下处理评论和图片的代码暂不可用（如果使用公司网络的话）
            // 处理评论列表中的图片
//            String commentListUrl = "https://club.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98&productId="+skuValue+"&score=0&sortType=5&page=0&pageSize=20&isShadowSku=0&fold=1";
//            itemList.addAll(getCommentImg(commentListUrl,itemName));
//            String itemDetailContent = doGetHtml(itemDetailUrl);
//            // 这里需要做判空处理
//            if (StringUtils.isNotEmpty(itemDetailContent)) {
//                // a标签
//                Document document = Jsoup.parse(itemDetailContent);
//                itemList.addAll(getHyperlinkList(document));
//
//                Elements imgElements = document.select("img");
//                for (Element element : imgElements) {
//                    String originSrc = element.attr("src");
//                    if (!StringUtil.isLegalUrl(originSrc)) {
//                        continue;
//                    }
//                    String newSrc = Constants.HTTPS + originSrc;
//                    if ((!redisService.checkHaveSameUrl(newSrc))) {
//                        redisService.saveUrlToRedis(newSrc);
//                        itemList.add(new Item(element.attr("alt"), newSrc));
//                    }
//                }
//            }
        }
        return itemList;
    }

    @Override
    public List<Item> getCommentImg(String url,String itemName) throws IOException {
        List<Item> list = new ArrayList<>();
        String html = doGetHtml(url);
        if (StringUtils.isEmpty(html)){
            return Collections.emptyList();
        }
        // 截取成json字符串
        String json2=html.substring(html.indexOf('(')+1,html.lastIndexOf(')'));
        // 获取评论
        JSONArray array = JSON.parseObject(json2).getJSONArray("comments");
        for (Object item : array) {
            //获取评论用户信息和照片
            String nickname = JSON.parseObject(item.toString()).getString("nickname");
            String userImage = JSON.parseObject(item.toString()).getString("userImage");
            String wholeUserImage = "https://"+userImage;
            if (!redisService.checkHaveSameUrl(wholeUserImage)){
                redisService.saveUrlToRedis(wholeUserImage);
                list.add(new Item(nickname,wholeUserImage));
            }
            JSONArray array1 = JSON.parseObject(item.toString()).getJSONArray("images");
            int imageOrder = 1;
            if (null == array1){
                continue;
            }
            for (Object item1 : array1) {
                String wholeImageUrl = Constants.HTTPS+JSON.parseObject(item1.toString()).getString("imgUrl");
                if (!redisService.checkHaveSameUrl(wholeImageUrl)){
                    redisService.saveUrlToRedis(wholeImageUrl);
                    list.add(new Item(itemName+"-"+imageOrder,wholeImageUrl));
                    imageOrder++;
                }
            }
        }
        return list;
    }

    @Override
    public List<Item> getHyperlinkList(Document document){
        List<Item> list = new ArrayList<>();
        Elements select = document.select("a[href]");
        for (Element element : select) {
            String originHref = element.attr("href");
            if (!StringUtil.isLegalUrl(originHref)) {
                continue;
            }
            String newHref = originHref.substring(0, 2).equals("//") ? Constants.HTTPS + originHref : originHref;
            if (!redisService.checkHaveSameUrl(newHref)) {
                redisService.saveUrlToRedis(newHref);
                list.add(new Item(element.text(), newHref));
            }
        }
        return list;
    }

    @Override
    public void crawlItemData(String baseUrl) throws IOException {
        List<Item> batchList = new ArrayList<>();
        List<Item> unitList = new ArrayList<>();
        int emptyDataCountFlag = 0;
        for (int i = 1; i < 501; i++) {
            String seedUrl = new StringBuffer(baseUrl).append(i).append(Constants.S_PARAM).append((i - 1) * 25 + 1).append(Constants.TAIL_PARAM).toString();
            logger.info("正在爬取的页面地址：" + seedUrl);
            String html = doGetHtml(seedUrl);
            //  解析页面，获取商品数据并存储
            if (!StringUtils.isBlank(html)) {
                unitList.addAll(parseItemHtml(html));
            }
            // 监控连续三个空HTML页面
            if (unitList.isEmpty()){
                emptyDataCountFlag++;
                if (emptyDataCountFlag >= 3){
                    itemService.saveBatchItems(batchList);
                    break;
                }
            }else {
                batchList.addAll(unitList);
                unitList.clear();
                emptyDataCountFlag = 0;
            }
            // 每20页批量入库并置空item容器
            if (i % 20 == 0) {
                itemService.saveBatchItems(batchList);
                batchList.clear();
            }
        }
        logger.info("爬取某分类下的数据结束");
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void crawlCategoryData(String url) throws IOException {
        logger.info("正在爬取所有分类信息");
        List<String> categoryNameList = new ArrayList<>();
        String html = doGetHtml(url);
        Document doc = Jsoup.parse(html);
        Elements eles = doc.select("body > div:nth-child(5) > div.main-classify > div.list > div.category-items.clearfix>div.col");
        for (Element element : eles) {
            Elements select = element.select("div>div");
            for (Element ele1 : select) {
                Elements select1 = ele1.select("div > div.mc > div:nth-child(3) > dl");
                for (Element e : select1) {
                    Elements select3 = e.select("dl > dd > a");
                    for (Element ele : select3) {
                        String title = ele.text();
                        String categoryUrl = ele.attr("href");
                        logger.info(categoryUrl);
                        if (StringUtils.isNotEmpty(title)) {
                            // 请求链接中搜索关键字部分若带有空格，JD服务端会反馈参数非法异常，故此处将空格格式化
                            title = title.replaceAll(" ", "");
                            categoryNameList.add(title);
                        }
                    }
                }
            }
        }
        dataMapper.clearCategoryList();
        itemService.saveBatchCategory(categoryNameList);
        logger.info("爬取分类信息完成");
    }

}

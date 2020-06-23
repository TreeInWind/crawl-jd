package com.kingstar.handler;

import com.kingstar.service.CrawlService;

/**
 * @Description:爬虫线程类
 * @Author: myl
 * @Date: 2020/6/17 16:21
 */
public abstract class CrawlThread extends Thread {

    /**
     * 种子URL
     */
    String seedUrl;

    /**
     * 爬虫服务
     */
    private CrawlService crawlService;

    /**
     * 自定义爬虫线程构造器
     *
     * @param seedUrl
     * @param crawlService
     * @return
     * @author yongliang
     */
    public CrawlThread(String seedUrl, CrawlService crawlService) {
        super();
        this.seedUrl = seedUrl;
        this.crawlService = crawlService;
    }

    @Override
    public void run() {
        this.doBiz(this.seedUrl, this.crawlService);
    }

    /**
     * 抽象方法，使用者实现该方法后使用该类
     *
     * @param seedUrl
     * @param crawlService
     * @return void
     * @author yongliang
     */
    public abstract void doBiz(String seedUrl, CrawlService crawlService);


}

package com.kingstar.handler;

import com.kingstar.service.CrawlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:爬虫服务调度处理器
 * @Author: myl
 * @Date: 2020/6/17 14:28
 */
@Component
public class CrawlDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(CrawlDispatcher.class);

    @Autowired
    private CrawlService crawlService;

    /**
     * 开启爬取京东数据服务
     *
     * @param
     * @return void
     * @author yongliang
     */
    public void dispatchThreadToCrawl(List<String> originSeedList) {
        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(20, 50, 20000,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(originSeedList.size() - 50));
        for (int i = 0; i < originSeedList.size(); i++) {
            CrawlThread crawlThread = new CrawlThread(originSeedList.get(i), crawlService) {
                @Override
                public void doBiz(String seedUrl, CrawlService crawlService) {
                    try {
                        crawlService.crawlItemData(seedUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.execute(crawlThread);
            logger.info("正在爬取的线程个数为：" + threadPool.getPoolSize() + ",队列中等待爬取的种子个数为" + threadPool.getQueue().size());
        }
        threadPool.shutdown();
    }


}

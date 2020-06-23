package com.kingstar.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:爬虫种子连接队列
 * @Author: myl
 * @Date: 2020/6/17 16:25
 */
public class CrawlSeedQueue {

    /**
     * 等在爬取的种子队列
     */
    private List<String> waitingSeedList;

    public List<String> getWaitingSeedList() {
        return waitingSeedList;
    }

    /**
     * 已爬取完毕的种子队列
     */
    private List<String> finishedSeedList = new ArrayList<>();


    public CrawlSeedQueue(List<String> waitingSeedList) {
        this.waitingSeedList = waitingSeedList;
    }

    /**
     * 添加等待中的种子连接
     *
     * @param seedUrl
     * @return void
     * @author yongliang
     */
    public void addWaitingSeed(String seedUrl) {
        this.waitingSeedList.add(seedUrl);
    }



    /**
     * 消耗一个爬虫种子
     * <p> 如果等待队列中有种子可消耗，则返回下标为0的种子；反之，返回null表示无种子可消耗 </p>
     *
     * @param
     * @return java.lang.String
     * @author yongliang
     */
    public synchronized String consumeWaitingSeed() {
        if (this.waitingSeedList.isEmpty()) {
            return null;
        }
        String headSeed = this.waitingSeedList.remove(0);
        this.finishedSeedList.add(headSeed);
        return headSeed;
    }

}

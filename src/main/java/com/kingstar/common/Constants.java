package com.kingstar.common;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.math.BigDecimal;

/**
 * @Description: 系统常量
 * @Author: myl
 * @Date: 2020/6/11 21:00
 */
public class Constants {

    /**
     * 0 字符串
     */
    public static final String ZERO_STRING = "0";


    /**
     * 系统占位符
     */
    public static final String PLACEHOLDER = "OK";


    /**
     * 爬虫请求连接,示例demo：https://search.jd.com/Search?keyword="+content+"&wq="+content+"&page="+n+"&s="+(1+(n-1)*30)+"&click=0&scrolling=y
     * https://search.jd.com/Search?keyword=复合机&wq=复合机&page=101&s=2501&click=0&scrolling=y
     */
    /**
     * root_url:根源连接
     */
    public static final String ORIGIN_URL = "https://search.jd.com/Search?keyword=";

    /**
     * 中间参数
     */
    public static final String MIDDLE_PARAM = "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&stock=1";

    /**
     * 分页参数
     */
    public static final String PAGE_PARAM = "&page=";

    /**
     * 表征所查页面之前有多少条信息
     */
    public static final String S_PARAM = "&s=";

    /**
     * 尾部参数
     */
    public static final String TAIL_PARAM = "&click=0&scrolling=y";


    /**
     * 超文本传输安全协议
     */
    public static final String HTTPS = "https:";



}

package com.kingstar.utils;

import com.kingstar.common.Constants;
import com.kingstar.common.RequestHeaderConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:String类型工具方法
 * @Author: myl
 * @Date: 2020/6/11 15:30
 */
public class StringUtil {

    /**
     * 获取UUID值
     *
     * @param
     * @return java.lang.String
     * @author yongliang
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取请求头常量数组中随机请求头
     *
     * @param
     * @return java.lang.String
     * @author yongliang
     */
    public static String getRandomUserAgent() {
        int totalNum = RequestHeaderConstants.userAgentArrays.length;
        //本来范围是[0,10)，整体+1之后变成了[1,n+1)，也就是[1,n]
        int order = new Random().nextInt(totalNum) + 1;
        return RequestHeaderConstants.userAgentArrays[order - 1];
    }

    /**
     * 判断是否为HTTP地址
     *
     * @param urls
     * @return boolean
     * @author yongliang
     */
    public static boolean isHttpUrl(String urls) {
        boolean isurl = false;
        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";//设置正则表达式
//        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))";

        Pattern pat = Pattern.compile(regex.trim());//对比
        Matcher mat = pat.matcher(urls.trim());
        isurl = mat.matches();//判断是否匹配
        return isurl;
    }


    /**
     * 判断URL是否合法
     * <p>true：合法；false：非法</p>
     *
     * @param src
     * @return boolean
     * @author yongliang
     */
    public static boolean isLegalUrl(String src) {
        if (StringUtils.isEmpty(src) || src.length() < 7) {
            return false;
        }
        if ("//".equals(src.substring(0, 2)) || Constants.HTTPS.equals(src.substring(0, 6))) {
            return true;
        }
        return false;
    }

}

package com.kingstar.handler;

import com.kingstar.common.BusinessException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description:数据校验
 * @Author: myl
 * @Date: 2020/6/11 16:22
 */
public class Assert {

    /**
     * 判断字符串是否为空
     *
     * @author yongliang
     * @param str：所要判断的字符串信息
     * @param message：字符串为空时的响应信息
     * @return void
     */
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message);
        }
    }

    /**
     * 判断参数是否为null
     *
     * @author yongliang
     * @param object：传入的参数对象
     * @param message：参数为null时的响应信息
     * @return void
     */
    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }
}

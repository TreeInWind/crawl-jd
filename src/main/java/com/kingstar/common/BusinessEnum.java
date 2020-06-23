package com.kingstar.common;

/**
 * @Description:业务枚举
 * @Author: myl
 * @Date: 2020/6/11 16:07
 */
public enum BusinessEnum {

    /**
     * 分类枚举
     */
    CATEGORY_PHONE("phone","手机"),
    /**
     * 可用状态
     */
    STATUS_ENABLE("ENABLE", "可用"),
    STATUS_UN_ENABLE("UN_ENABLE", "不可用")
    ;

    private String code;

    private String message;

    BusinessEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}


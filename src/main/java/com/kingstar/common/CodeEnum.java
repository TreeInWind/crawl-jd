package com.kingstar.common;

/**
 * @Description:自定义返回码实体
 * @Author: myl
 * @Date: 2020/6/11 14:40
 */
public enum CodeEnum {

    /**
     * 响应成功
     */
    E_200("200", "success"),

    /**
     * 请求有误
     */
    E_400("400", "400 Bad Request"),


    /**
     * 无权限
     */
    E_401("401", "401 Unauthorized"),

    /**
     * 服务超时
     */
    E_402("402", "402 sessionTimeOut"),

    /**
     * 资源未找到
     */
    E_404("404", "404 Not Found"),

    /**
     * 服务器错误
     */
    E_500("500", "Intertnal Server Error");

    private String code;

    private String message;

    CodeEnum(String code, String message) {
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

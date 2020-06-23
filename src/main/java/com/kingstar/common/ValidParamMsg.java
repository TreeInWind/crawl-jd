package com.kingstar.common;

import java.io.Serializable;

/**
 * @Description: 参数校验对象
 * @Author: myl
 * @Date: 2020/6/11 23:15
 */
public class ValidParamMsg implements Serializable {

    private static final long serialVersionUID = -1502594582561781824L;

    /**
     * 校验的字段值
     */
    private String field;

    /**
     * 对象名字
     */
    private String objectName;

    /**
     * 异常信息
     */
    private String message;

    public ValidParamMsg() {
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

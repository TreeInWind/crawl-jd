package com.kingstar.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:参数校验响应的异常对象
 * @Author: myl
 * @Date: 2020/6/11 23:17
 */
public class ErrorMsg {

    private static final long serialVersionUID = -2714106708828458449L;

    /**
     * 异常码
     */
    private String errCode;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 验证的错误信息
     */
    private List<ValidParamMsg> validParams = new ArrayList<>();

    public ErrorMsg() {
    }

    public ErrorMsg(CodeEnum codeEnum) {
        this.errCode = codeEnum.getCode();
        this.msg = codeEnum.getMessage();
    }

    public ErrorMsg(CodeEnum codeEnum, String message) {
        this.errCode = codeEnum.getCode();
        this.msg = message;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ValidParamMsg> getValidParams() {
        return validParams;
    }

    public void setValidParams(List<ValidParamMsg> validParams) {
        this.validParams = validParams;
    }

}

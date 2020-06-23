package com.kingstar.common;

/**
 * @Description:自定义响应体
 * @Author: myl
 * @Date: 2020/6/11 14:40
 */
public class ResponseData<T> {

    /**
     * 状态码
     */
    private String code;

    /**
     * 响应的信息
     */
    private String msg;

    /**
     * 是否响应成功
     */
    private Boolean success;


    /**
     * 响应的数据信息
     */
    private T data;

    /**
     * 响应成功
     *
     * @param <T> 泛型
     * @return 状态码: 20000，状态信息:响应成功
     */
    public static <T> ResponseData<T> ok() {
        return new ResponseData<>(CodeEnum.E_200, true);
    }

    /**
     * 返回请求结果
     *
     * @param data 返回得到数据信息
     * @param <T>  泛型
     * @return 状态码：200， 状态信息：响应成功， 数据：结果数据
     */
    public static <T> ResponseData<T> ok(T data) {
        return new ResponseData<>(CodeEnum.E_200, data, true);
    }

    /**
     * 响应失败
     *
     * @param <T> 泛型
     * @return 状态码：500， 状态信息：系统异常
     */
    public static <T> ResponseData<T> error() {
        return new ResponseData<>(CodeEnum.E_500, false);
    }

    /**
     * 响应失败
     *
     * @param codeEnum 状态码和状态信息的响应对象
     * @param <T>      泛型
     * @return 响应对象对应的状态码和状态信息
     */
    public static <T> ResponseData<T> error(CodeEnum codeEnum) {
        return new ResponseData<>(codeEnum, false);
    }

    /**
     * 自定义异常信息
     *
     * @param codeEnum 状态码和状态信息的响应对象
     * @param errorMsg 自定义的异常信息
     * @param <T>      泛型
     * @return 状态码和自定义的异常信息
     */
    public static <T> ResponseData<T> error(CodeEnum codeEnum, String errorMsg) {
        return new ResponseData<>(codeEnum, errorMsg, false);
    }

    private ResponseData(CodeEnum codeEnum, Boolean success) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMessage();
        this.success = success;
    }

    private ResponseData(CodeEnum codeEnum, T data, Boolean success) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMessage();
        this.data = data;
        this.success = success;
    }

    private ResponseData(CodeEnum codeEnum, String errorMsg, Boolean success) {
        this.code = codeEnum.getCode();
        this.msg = errorMsg;
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}

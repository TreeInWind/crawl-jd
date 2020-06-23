package com.kingstar.handler;

import com.kingstar.common.CodeEnum;
import com.kingstar.common.ErrorMsg;
import com.kingstar.common.ValidParamMsg;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:在控制器层横切实现全局异常捕捉
 * @Author: myl
 * @Date: 2020/6/11 23:14
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public ErrorMsg exception(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        ErrorMsg errorMsg = new ErrorMsg(CodeEnum.E_400, "请求参数有误");
        allErrors.forEach(objectError -> {
            ValidParamMsg msg = new ValidParamMsg();
            FieldError fieldError = (FieldError) objectError;
            String field = fieldError.getField();
            msg.setField(field);
            msg.setMessage(fieldError.getDefaultMessage());
            msg.setObjectName(fieldError.getObjectName());
            errorMsg.getValidParams().add(msg);
        });
        return errorMsg;
    }
}

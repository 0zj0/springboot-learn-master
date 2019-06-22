package com.example.demo.exception;

import com.example.demo.entity.MsgContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author zhangjie
 * @date 2019/6/21 14:46
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {

    /**
     * 自定义异常获取类
     * @param ex 异常
     * @return com.example.demo.entity.MsgContext
     */
    @ExceptionHandler(ValidException.class)
    @ResponseBody
    public MsgContext handleValidException(ValidException ex) {
        log.error("[数据校验异常]code={},message={}",ex.getCode(),ex.getMessage());
        MsgContext msgContext = new MsgContext();
        msgContext.setCode(ex.getCode());
        msgContext.setMessage(ex.getMessage());
        return msgContext;
    }

    /**
     * 系统异常捕获处理
     * @param ex 异常
     * @return com.example.demo.entity.MsgContext
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MsgContext systemException(Exception ex) {
        log.error("[数据校验异常],message={}",ex.getMessage());
        MsgContext msgContext = new MsgContext();
        msgContext.setCode(0);
        msgContext.setMessage(ex.getMessage());
        return msgContext;
    }
}

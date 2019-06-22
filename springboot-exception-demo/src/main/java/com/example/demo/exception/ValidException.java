package com.example.demo.exception;

import lombok.Getter;

/**
 * 校验异常获取类
 * @author zhangjie
 * @date 2019/6/21 14:35
 */
@Getter
public class ValidException extends RuntimeException{
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误响应消息
     */
    private Object responseInfo;

    public ValidException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public ValidException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ValidException(String message, Integer code, Object responseInfo) {
        super(message);
        this.code = code;
        this.responseInfo = responseInfo;
    }

    public Integer getCode() {
        return code;
    }
}

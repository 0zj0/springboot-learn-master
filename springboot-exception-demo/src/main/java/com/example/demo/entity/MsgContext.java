package com.example.demo.entity;

import lombok.Data;

/**
 * @author zhangjie
 * @date 2019/6/21 14:49
 */
@Data
public class MsgContext {

    /**
     * 提示码
     */
    private Integer code;

    /**
     * 提示描述
     */
    private String message;

    /**
     * 返回携带信息
     */
    private Object data;

}

package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangjie
 * @date 2019/5/17 16:58
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = -8842366031727014054L;

    /**
     * 系统用户id
     */
    @TableId(value = "suId", type = IdType.AUTO)
    private int suId;
    /**
     * 系统用户名称
     */
    private String suName;
    /**
     * 系统用户登录密码
     */
    private String password;
    /**
     * 用户类型：1：普通，2-系统
     */
    private int roleType = 1;
    /**
     * 系统用户真实姓名
     */
    private String realName;
    /**
     * 状态：1：可用，2：禁用，3：删除状态
     */
    private int status = 1;


}

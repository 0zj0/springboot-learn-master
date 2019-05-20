package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 全局枚举类
 * </p>
 *
 * @author zj
 * @since 2019-05-17
 */
@TableName("global_enum")
@Data
public class GlobalEnum extends Model<GlobalEnum> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "enumId", type = IdType.AUTO)
    private Integer enumId;
    /**
     * 枚举值key
     */
    @TableField(value = "enumKey")
    private String enumKey;
    /**
     * 枚举值名称
     */
    @TableField(value = "enumName")
    private String enumName;
    /**
     * 枚举类型
     */
    @TableField(value = "enumType")
    private String enumType;
    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
    /**
     * 添加时间
     */
    @TableField(value = "ctime")
    private Long ctime;

    @Override
    protected Serializable pkVal() {
        return this.enumId;
    }
}

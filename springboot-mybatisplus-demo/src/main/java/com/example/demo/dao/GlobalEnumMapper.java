package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.GlobalEnum;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangjie
 * @date 2019/5/17 17:04
 */
@Mapper
public interface GlobalEnumMapper extends BaseMapper<GlobalEnum> {

    IPage<GlobalEnum> selectPage(Page page);
}

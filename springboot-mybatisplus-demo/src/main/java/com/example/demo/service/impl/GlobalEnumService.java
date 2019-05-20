package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.GlobalEnumMapper;
import com.example.demo.entity.GlobalEnum;
import com.example.demo.service.IGlobalEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangjie
 * @date 2019/5/18 15:47
 */
@Service
public class GlobalEnumService implements IGlobalEnumService {

    @Autowired
    private GlobalEnumMapper globalEnumMapper;


    @Override
    public GlobalEnum selectById(Integer id) {
        return globalEnumMapper.selectById(id);
    }

    @Override
    public List<GlobalEnum> selectList() {
        QueryWrapper<GlobalEnum> wrapper = new QueryWrapper<>();
        wrapper.eq("enumType","ACTIVITY");
        //wrapper.eq("enumKey","ACT_DIRECT_VOUCHER");
        wrapper.in("enumKey",new Object[]{"ACT_DIRECT_VOUCHER","ACT_SCRATCH_CARD","ACT_SCAVENGING_SURPRISE"});
        return globalEnumMapper.selectList(wrapper);
    }

    @Override
    public IPage<GlobalEnum> selectPage(Page page) {
        page.setSize(10);
        page.setCurrent(1);
        IPage<GlobalEnum> enumPage = globalEnumMapper.selectPage(page,new QueryWrapper<>());
        return enumPage;
        //return globalEnumMapper.selectPage(page);
    }
}


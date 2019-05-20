package com.example.demo.service.impl;

import com.example.demo.dao.GlobalEnumMapper;
import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangjie
 * @date 2019/5/17 17:30
 */
@Service
public class SysUserService implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserDao;

    @Override
    public List<SysUser> getSysUserList() {
        return sysUserDao.getSysUserList();
    }
}

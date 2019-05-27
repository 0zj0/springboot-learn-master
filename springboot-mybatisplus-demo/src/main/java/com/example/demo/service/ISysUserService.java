package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.SysUser;

import java.util.List;

/**
 * @author zhangjie
 * @date 2019/5/17 17:30
 */
public interface ISysUserService {

    List<SysUser> getSysUserList();

    IPage<SysUser> page(IPage<SysUser> page,SysUser sysUser);

    IPage<SysUser> getUserPage(IPage<SysUser> page, SysUser sysUser);

    List<SysUser> getSysUserList2(SysUser sysUser);

    int addUser(SysUser sysUser);

    int addBatchUser(List<SysUser> list);
}

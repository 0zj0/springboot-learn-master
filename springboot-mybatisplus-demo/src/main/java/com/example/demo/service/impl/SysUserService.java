package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    @Autowired(required = false)
    private SysUserMapper sysUserDao;

    @Override
    public List<SysUser> getSysUserList() {
        return sysUserDao.getSysUserList();
    }

    @Override
    public IPage<SysUser> page(IPage<SysUser> page, SysUser sysUser) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("","").eq("","");
        wrapper.in("column","1","2","3");
        wrapper.like("column","%"+"name"+"%");
        return sysUserDao.selectPage(page,wrapper);
    }

    @Override
    public IPage<SysUser> getUserPage(IPage<SysUser> page, SysUser sysUser) {
        //方式一：
        //return sysUserDao.getUserPage(page,sysUser);
        //方式二：(失败)
        //return sysUserDao.getUserPage2(page,sysUser);
        //方式三：
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        return sysUserDao.selectPage(page,wrapper);
    }

    @Override
    public List<SysUser> getSysUserList2(SysUser sysUser) {
        SysUser sysUser1 = sysUserDao.selectById(1);
        System.out.println(sysUser1);
        //方式一：
        /*QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if(sysUser!=null && !StringUtils.isEmpty(sysUser.getRealName())){
            wrapper.like("realName", "%"+sysUser.getRealName()+"%");
        }
        return sysUserDao.selectList(wrapper);*/
        //方式二：注解实现
        //return sysUserDao.getSysUserList2(sysUser);
        //方式三：mapperxml sql 实现
        //return sysUserDao.getSysUserList3(sysUser);
        //方式四：mapperxml sql 实现2
        return sysUserDao.getSysUserList4(sysUser);
    }

    @Override
    public int addUser(SysUser sysUser) {
        //方式一： mybatis 自带方法
       /* int id = sysUserDao.insert(sysUser);
        System.out.println(sysUser.getSuId());  //主键id
        return id;*/
        //方式二：  dao注解实现
        /*int id = sysUserDao.addUser1(sysUser);
        System.out.println(sysUser.getSuId());  //主键id
        return id;*/
        //方式三： mapperxml sql 实现
        int id = sysUserDao.addUser2(sysUser);
        System.out.println(sysUser.getSuId());  //主键id
        return id;
    }

    @Override
    public int addBatchUser(List<SysUser> list) {
        int i = sysUserDao.addBatchUser(list);
        list.stream().forEach(item -> {
            System.out.println(item.getSuId());
        });
        return i;
    }
}

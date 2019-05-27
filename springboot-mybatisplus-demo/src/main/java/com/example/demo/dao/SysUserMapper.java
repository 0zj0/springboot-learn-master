package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhangjie
 * @date 2019/5/17 17:04
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT  * FROM sys_user where  status = 1;")
    List<SysUser> getSysUserList();


    IPage<SysUser> getUserPage(IPage<SysUser> page, SysUser sysUser);

    @Select("select * from sys_user ")
    IPage<SysUser> getUserPage2(IPage<SysUser> page, SysUser sysUser);

    @SelectProvider(type = UserSql.class,method = "getSysUserList2")
    List<SysUser> getSysUserList2(SysUser sysUser);

    List<SysUser> getSysUserList3(SysUser sysUser);

    List<SysUser> getSysUserList4(@Param("sysUser") SysUser sysUser);

    @Insert("INSERT INTO sys_user ( suName, password, roleType, realName, status ) " +
            "VALUES ( #{suName}, #{password}, #{roleType}, #{realName}, #{status} ) ")
    @Options(useGeneratedKeys = true,keyProperty = "suId",keyColumn = "id") //返回主键id
    int addUser1(SysUser sysUser);

    int addUser2(SysUser sysUser);

    int addBatchUser(List<SysUser> list);
}

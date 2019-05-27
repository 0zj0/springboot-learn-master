package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import org.springframework.util.StringUtils;

/**
 * @author zhangjie
 * @date 2019/5/27 14:00
 */
public class UserSql {

    public static String getSysUserList2(SysUser sysUser){

        StringBuilder sql = new StringBuilder("");
        sql.append("select * ")
                .append(" from sys_user ");
        if(sysUser == null ){
            return sql.toString();
        }
        if(!StringUtils.isEmpty(sysUser.getRealName())){
            //sql.append("where realName like '%#{realName}%'");    //失败报错
            sql.append("where realName like concat('%',#{realName},'%')");
        }
        return sql.toString();
    }

}

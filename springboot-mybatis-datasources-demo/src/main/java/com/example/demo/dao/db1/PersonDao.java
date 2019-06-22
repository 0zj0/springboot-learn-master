package com.example.demo.dao.db1;

import com.example.demo.entity.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangjie
 * @since 2019-05-28
 */
public interface PersonDao extends BaseMapper<Person> {

    @Select("select * from person ")
    List<Person> getList();

    int getListCnt();

    @Update("Update person set name = #{name} where id = #{id} ")
    int update1(@Param("id") int id,@Param("name") String name);
}

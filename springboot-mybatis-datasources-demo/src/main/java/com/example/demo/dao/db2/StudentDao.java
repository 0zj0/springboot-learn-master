package com.example.demo.dao.db2;

import com.example.demo.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangjie
 * @since 2019-05-28
 */
@Mapper
public interface StudentDao extends BaseMapper<Student> {

    @Select("select * from student")
    List<Student> getList();

    boolean update1(@Param("id") int id,@Param("name") String name);

    boolean update2(@Param("id") int id,@Param("name") String name);
}

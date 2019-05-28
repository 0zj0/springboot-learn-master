package com.example.demo.dao.db2;

import com.example.demo.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface StudentDao extends BaseMapper<Student> {

    @Select("select * from student")
    List<Student> getList();
}

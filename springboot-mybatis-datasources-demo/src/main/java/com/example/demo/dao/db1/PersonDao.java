package com.example.demo.dao.db1;

import com.example.demo.entity.Person;
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
public interface PersonDao extends BaseMapper<Person> {

    @Select("select * from person ")
    List<Person> getList();
}

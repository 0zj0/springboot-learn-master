package com.example.demo.service;

import com.example.demo.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangjie
 * @since 2019-05-28
 */
public interface IPersonService extends IService<Person> {


    void getList();


    int getListCnt();
}

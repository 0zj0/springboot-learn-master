package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.GlobalEnum;

import java.util.List;

/**
 * @author zhangjie
 * @date 2019/5/18 15:47
 */
public interface IGlobalEnumService {

    /**
     * 通过主键id获取数据
     * @param id
     * @return
     */
    GlobalEnum selectById(Integer id);

     /**
      *
      * @return
      * @author 张杰
      * @date 2019/05/18 15:58
      */
    List<GlobalEnum> selectList();

     /**
      * 分页
      * @return
      * @author 张杰
      * @date 2019/05/20 10:44
      */
    IPage<GlobalEnum> selectPage(Page page);
}

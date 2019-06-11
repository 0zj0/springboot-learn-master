package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.db2.StudentDao;
import com.example.demo.entity.Student;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangjie
 * @since 2019-05-28
 */
@Service
public class StudentService2 extends ServiceImpl<StudentDao, Student> implements IStudentService {

    @Autowired(required = false)
    private StudentDao studentDao;

    @Override
    public void getList() {
       List<Student> list = studentDao.getList();
        System.out.println(list.size());
    }
}

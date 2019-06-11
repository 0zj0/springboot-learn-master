package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.db1.PersonDao;
import com.example.demo.entity.Person;
import com.example.demo.service.IPersonService;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class PersonService extends ServiceImpl<PersonDao, Person> implements IPersonService {

    @Autowired(required = false)
    private PersonDao personDao;

    @Autowired
    @Qualifier("studentService")
    private IStudentService studentService;

    @Override
    public void getList() {
        List<Person> list = personDao.getList();
        for(Person person : list){
            System.out.println(person.toString());
        }
    }

    @Override
    public int getListCnt() {
        studentService.getList();
        return personDao.getListCnt();
    }
}

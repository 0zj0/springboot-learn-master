package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.config.datasource.MultipleTransactionSupport;
import com.example.demo.config.transaction.CustomTransaction;
import com.example.demo.dao.db1.PersonDao;
import com.example.demo.dao.db2.StudentDao;
import com.example.demo.entity.Person;
import com.example.demo.entity.Student;
import com.example.demo.service.IPersonService;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Autowired(required = false)
    private StudentDao studentDao;

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

    @Override
    @CustomTransaction(value = {"transactionManagerOne","transactionManagerTwo"})
    public void test() {

        /*boolean b = personDao.update1(1,"name1111") > 0;
        b = b && personDao.update1(2,"name2222") > 0;
        b = b && studentDao.update1(1,"student1111");
        b = b && studentDao.update2(2,"student2student2student2student2student2student2");*/
        Person person1 = new Person("name001",12,"addr001");
        Person person2 = new Person("name002",13,"addr002");
        Student student1 = new Student("name0001",15,"addr0001");
        Student student2 = new Student("name0002",16,"addr0002");
        /*boolean b =  this.save(person1);
        b = b && this.save(person2);

        b = b && studentService.save(student1);
        b = b && studentService.save(student2);*/

        boolean b =  this.saveBatch(Arrays.asList(person1));
        b = b && this.saveBatch(Arrays.asList(person2));

        b = b && studentService.saveBatch(Arrays.asList(student1));
        b = b && studentService.saveBatch(Arrays.asList(student2));

        return;
    }

    @Autowired(required = false)
    private MultipleTransactionSupport transactionSupport;

    @Override
    public void test2() {
        boolean b =true;
        MultipleTransactionSupport.MultipleTransactionManager multipleTransactionManager =transactionSupport.beginTransactionMultiple(transactionSupport.getTxGroupOne(),transactionSupport.getTxGroupTwo());
        try {
            Person person1 = new Person("name00111",12,"addr001111");
            Person person2 = new Person("name00222",13,"addr002222");
            Student student1 = new Student("name000111",15,"addr0001111");
            Student student2 = new Student("name000222name000222name000222name000222name000222name000222",16,"addr0002222");


            b =  this.save(person1);
            b = b && this.save(person2);

            b = b && studentService.save(student1);
            b = b && studentService.save(student2);

        } catch (Exception e) {
            b = false;
        } finally {
            transactionSupport.commitOrRollMultiple(multipleTransactionManager,b);
        }
    }
}

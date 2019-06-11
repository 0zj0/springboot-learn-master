package com.example.demo.controller;


import com.example.demo.service.IPersonService;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangjie
 * @since 2019-05-28
 */
@RestController
@RequestMapping("/demo/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private IStudentService studentService;

    @GetMapping("/test")
    public String test(){
       /* personService.getList();
        System.out.println("********************************");
        System.out.println("********************************");
        studentService.getList();
        return "111";*/
        /*List<Person> list = personService.getlist();*/
        int size = personService.getListCnt();
        System.out.println(size);
        /*List<Person> addList = new ArrayList<>();
        for(int i= 0; i<list.size(); i++){
            if(i>= 2){
                break;
            }
            Person person = list.get(i);
            person.setId(null);
            addList.add(person);
        }
        boolean b = personService.saveBatch(addList);
        System.out.println(b);
        for(Person person : addList){
            System.out.println("***********"+person.getId());
        }*/
        /*Person person = list.get(0);
        person.setId(null);
        boolean b = personService.save(person);
        System.out.println(person.toString());*/

        return "212222";
    }

}


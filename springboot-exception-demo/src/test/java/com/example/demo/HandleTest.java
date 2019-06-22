package com.example.demo;

import com.example.demo.util.ValidationUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangjie
 * @date 2019/6/21 15:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HandleTest {

    @Test
    public void test(){
        //自定义异常
        ValidationUtil.assertFalse(true,"boolean判断处理");
    }

}

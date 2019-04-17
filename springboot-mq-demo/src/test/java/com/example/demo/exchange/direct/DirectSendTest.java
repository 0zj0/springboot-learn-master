package com.example.demo.exchange.direct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangjie
 * @date 2019/4/17 13:56
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectSendTest {

    @Autowired
    private DirectSend directSend;

    @Test
    public void directSend1() {
        directSend.directSend1();
    }

    @Test
    public void directSend2() {
        directSend.directSend2();
    }

    @Test
    public void directSend3() {
        directSend.directSend3();
    }

    @Test
    public void directSend4() {
        directSend.directSend4();
    }
}
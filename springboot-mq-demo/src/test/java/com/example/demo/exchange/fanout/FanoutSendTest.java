package com.example.demo.exchange.fanout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangjie
 * @date 2019/4/17 16:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutSendTest {

    @Autowired
    private FanoutSend fanoutSend;

    @Test
    public void fanoutSend1() {
        fanoutSend.fanoutSend1();
    }

    @Test
    public void fanoutSend2() {
        fanoutSend.fanoutSend2();
    }
}
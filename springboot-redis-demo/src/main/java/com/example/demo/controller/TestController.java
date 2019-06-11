package com.example.demo.controller;

import com.example.demo.config.lock.RedissLockUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjie
 * @date 2019/6/11 19:53
 */
@RestController
public class TestController {

    private final String lock_pre = "lock_";

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDate(){
        return sdf.format(new Date());
    }

    @GetMapping("/testlock")
    public String testLock(){
        System.out.println(1111);
        String name = "zj";

        String key = lock_pre + name;
        new Thread(new Runnable() {

            @Override
            public void run() {

                RedissLockUtil.lock(key, TimeUnit.MINUTES, 10);

                System.out.println(getCurrentDate()+" "+name+" begin...");
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(2000);
                        System.out.println(getCurrentDate()+" "+name+" "+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(getCurrentDate()+" "+name+" end...");

                RedissLockUtil.unlock(key);
            }
        }).start();

        return "testlock";
    }

    //localhost:8093/redisDemo/testlock2
    @GetMapping("/testlock2")
    public String testLock2(){

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    int num = Integer.parseInt(Thread.currentThread().getName());
                    int index = num % 3;
                    String key = lock_pre + index;
                    System.out.println(key);
                    RedissLockUtil.lock(key, TimeUnit.MINUTES, 10);
                    System.out.println(getCurrentDate()+" "+key+" "+num+" begin...");
                    try {
                        Thread.sleep(2000);
                        //System.out.println(getCurrentDate()+" "+key+" "+num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getCurrentDate()+" "+key+" "+num+" end...");
                    RedissLockUtil.unlock(key);
                }
            },String.valueOf(i)).start();

        }
        return "testlock";
    }

}

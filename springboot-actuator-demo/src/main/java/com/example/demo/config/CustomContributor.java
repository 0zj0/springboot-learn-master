package com.example.demo.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/5/2910:25
 */
@Component
public class CustomContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("test","This is a actuator test!").build();
    }
}

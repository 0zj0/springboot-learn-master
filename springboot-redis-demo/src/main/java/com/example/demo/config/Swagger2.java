package com.example.demo.config;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/5/29 16:23
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 集成Swagger在线api调试,访问url:http://localhost:${port}/swagger-ui.html
 * 1.使用注解@ApiIgnore忽略不想显示的api接口方法
 * 2.注解@ApiOperation和@ApiParam可以设置api接口方法说明
 */
@EnableSwagger2
@Configuration
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))    // 这是扫描注解的配置，即你的API接口位置。
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder().title("redis学习测试api")
                .description("在线调试，快速开发")
                .version("1.0")
                .build();
    }

}

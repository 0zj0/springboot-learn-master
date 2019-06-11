package com.example.demo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author zhangjie
 * @date 2019/5/17 17:02
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        //设置方言类型
        page.setDialectType("mysql");
        return page;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Primary
    public DataSource dataSourceOne(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSourceTwo(){
        return DataSourceBuilder.create().build();
    }
}

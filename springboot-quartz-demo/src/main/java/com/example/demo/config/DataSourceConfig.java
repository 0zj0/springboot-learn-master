/*
package com.example.demo.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

*/
/**
 * @author zhangjie
 * @date 2019/3/29 10:49
 *//*

@Configuration
public class DataSourceConfig {

    @Autowired
    private QuartzDataSourceProperty quartzDataSourceProperty;

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setUrl(quartzDataSourceProperty.getUrl());
        dataSource.setDriverClassName(quartzDataSourceProperty.getDriver());
        dataSource.setUsername(quartzDataSourceProperty.getUser());
        dataSource.setPassword(quartzDataSourceProperty.getPassword());
        dataSource.setInitialSize(quartzDataSourceProperty.getMaxConnections());
        return dataSource;
    }

}
*/

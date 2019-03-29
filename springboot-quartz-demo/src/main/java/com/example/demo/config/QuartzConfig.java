package com.example.demo.config;

//import com.example.demo.quartz.TestOneTriggerConfig;
import com.example.demo.quartz.TestTwoTriggerConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * 分布式定时任务管理配置
 * @author zhangjie
 * @date 2019/3/26 15:20
 */
@Configuration
@EnableAutoConfiguration
public class QuartzConfig {

    @Autowired
    private QuartzJobFactory quartzJobFactory;

    //@Autowired
    //private TestOneTriggerConfig testOneTriggerConfig;

    @Autowired
    private TestTwoTriggerConfig testTwoTriggerConfig;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(quartzDataSource());
        //使job实例支持spring 容器管理
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactoryBean.setJobFactory(quartzJobFactory);
        schedulerFactoryBean.setQuartzProperties(properties());
        // 延迟10s启动quartz
        schedulerFactoryBean.setStartupDelay(10);
        schedulerFactoryBean.setTriggers(
                //testOneTriggerConfig.cronTriggerFactoryBean().getObject(),
                testTwoTriggerConfig.cronTriggerFactoryBean().getObject()
        );
        return schedulerFactoryBean;
    }


    /*@Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws IOException, SchedulerException {
        //初始化bean并启动scheduler
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }
*/
    @Bean
    public Properties properties() throws IOException {
        Properties prop = new Properties();
        prop.load(new ClassPathResource("/quartz.properties").getInputStream());
        return prop;
    }

    //quartz使用的是c3p0的连接池
    public ComboPooledDataSource quartzDataSource() throws Exception{
        ComboPooledDataSource myDs=new ComboPooledDataSource();
        /*myDs.setJdbcUrl(prop.getProperty("org.quartz.dataSource.myDs.url"));
        myDs.setDriverClass(prop.getProperty("org.quartz.dataSource.myDs.driver"));
        myDs.setUser(prop.getProperty("org.quartz.dataSource.myDs.user"));
        myDs.setPassword(prop.getProperty("org.quartz.dataSource.myDs.password"));
        myDs.setMaxPoolSize(Integer.valueOf(prop.getProperty("org.quartz.dataSource.myDs.maxConnections")));
        */
        myDs.setJdbcUrl("jdbc:mysql://192.168.4.98:3306/quartz_test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true");
        myDs.setDriverClass("com.mysql.jdbc.Driver");
        myDs.setUser("root");
        myDs.setPassword("123456");
        myDs.setMaxPoolSize(5);
        return myDs;
    }

    /**
     * 设置quartz属性
     */
    /*public Properties quartzProperties() throws IOException {
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "ServerScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
        prop.put("org.quartz.scheduler.instanceId", "NON_CLUSTERED");
        prop.put("org.quartz.scheduler.jobFactory.class", "org.quartz.simpl.SimpleJobFactory");
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        prop.put("org.quartz.jobStore.isClustered", "true");
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "5");
        return prop;
    }*/

}

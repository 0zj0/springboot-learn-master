package com.example.demo.quartz;

import com.example.demo.job.TestTwoJob;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * @author zhangjie
 * @date 2019/3/29 11:18
 */
@Configuration
@EnableAutoConfiguration
public class TestTwoTriggerConfig {

    public static final String TRIGGER_NAME="test2_trigger";
    public static final String TRIGGER_GROUP="test2_trigger_group";
    public static final String JOB_NAME="test2_job";
    public static final String JOB_GROUP="test2_group";
    public static final String JOB_CRON="0/15 * * * * ? ";

    @Bean(name = "test2Trigger")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean cronTriggerFactoryBean=new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName(TRIGGER_NAME);
        cronTriggerFactoryBean.setGroup(TRIGGER_GROUP);
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression(JOB_CRON);
        return cronTriggerFactoryBean;
    }

    @Bean(name = "test2Detail")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean jobDetailFactoryBean=new JobDetailFactoryBean();
        jobDetailFactoryBean.setName(JOB_NAME);
        jobDetailFactoryBean.setGroup(JOB_GROUP);
        jobDetailFactoryBean.setJobClass(TestTwoJob.class);
        jobDetailFactoryBean.setDurability(true);//必须设置为true，如果为false，当没有活动的触发器与之关联时会在调度器中会删除该任务
        return jobDetailFactoryBean;
    }

}

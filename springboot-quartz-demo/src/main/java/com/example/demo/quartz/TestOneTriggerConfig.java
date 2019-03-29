/*
package com.example.demo.quartz;

import com.example.demo.job.TestOneJob;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

*/
/**
 * @author zhangjie
 * @date 2019/3/29 11:18
 *//*

//@Configuration
//@EnableAutoConfiguration
public class TestOneTriggerConfig {

    public static final String TRIGGER_NAME="test1_trigger";
    public static final String TRIGGER_GROUP="test1_trigger_group";
    public static final String JOB_NAME="test1_job";
    public static final String JOB_GROUP="test1_group";
    public static final String JOB_CRON="0 0/1 * * * ? ";

    @Bean(name = "test1Trigger")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean cronTriggerFactoryBean=new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName(TRIGGER_NAME);
        cronTriggerFactoryBean.setGroup(TRIGGER_GROUP);
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression(JOB_CRON);
        return cronTriggerFactoryBean;
    }

    @Bean(name = "test1Detail")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean jobDetailFactoryBean=new JobDetailFactoryBean();
        jobDetailFactoryBean.setName(JOB_NAME);
        jobDetailFactoryBean.setGroup(JOB_GROUP);
        jobDetailFactoryBean.setJobClass(TestOneJob.class);
        jobDetailFactoryBean.setDurability(true);//必须设置为true，如果为false，当没有活动的触发器与之关联时会在调度器中会删除该任务
        return jobDetailFactoryBean;
    }

}
*/

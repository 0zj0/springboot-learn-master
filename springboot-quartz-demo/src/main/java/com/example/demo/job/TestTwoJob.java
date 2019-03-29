package com.example.demo.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangjie
 * @date 2019/3/29 11:22
 */
@DisallowConcurrentExecution        //quartz任务执行完之后再执行下一轮任务
public class TestTwoJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(TestTwoJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("TestTwoJob.begin...");
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("TestTwoJob.end...");
    }
}

package com.example.demo.config.transaction;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.example.demo.config.ApplicationContextHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Stack;

/**
 * 事务处理aop切口
 * @author zhangjie
 * @date 2019/6/17 20:33
 */
@Aspect
@Configuration
public class TransactionAop {

    private static final Logger logger = LoggerFactory.getLogger(TransactionAop.class);

    @Pointcut("@annotation(com.example.demo.config.transaction.CustomTransaction)")
    public void CustomTransaction(){
    }

    /*@Before(value = "CustomTransaction()&&@annotation(annotation)")
    public void doBefore(ProceedingJoinPoint thisJoinPoint, CustomTransaction annotation){
        System.out.println("*************自定义事务注解before*************");
    }*/

    @Around(value = "CustomTransaction()&&@annotation(annotation)")
    public Object twiceAsOld(ProceedingJoinPoint thisJoinPoint, CustomTransaction annotation) throws Throwable {
        System.out.println("*************自定义事务注解监听Around*************");

        Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack = new Stack<DataSourceTransactionManager>();
        Stack<TransactionStatus> transactionStatusStack = new Stack<TransactionStatus>();
        try {
            if (!openTransaction(dataSourceTransactionManagerStack, transactionStatusStack, annotation)) {
                return null;
            }
            Object ret = thisJoinPoint.proceed();
            commit(dataSourceTransactionManagerStack, transactionStatusStack);

            return ret;
        } catch (Throwable e) {
            rollback(dataSourceTransactionManagerStack, transactionStatusStack);
            logger.error(String.format("MultiTransactionalAspect, method:%s-%s occors error:",
                    thisJoinPoint.getTarget().getClass().getSimpleName(), thisJoinPoint.getSignature().getName()), e);
            throw e;
        }
    }

    /**
     * 开启事务处理方法
     * @param dataSourceTransactionManagerStack
     * @param transactionStatuStack
     * @param multiTransactional
     * @return
     */
    private boolean openTransaction(Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,
                                    Stack<TransactionStatus> transactionStatuStack, CustomTransaction multiTransactional) {
        System.out.println("*************事务open*************");
        String[] transactionMangerNames = multiTransactional.value();
        if (ArrayUtils.isEmpty(multiTransactional.value())) {
            return false;
        }

        for (String beanName : transactionMangerNames) {
            System.out.println("*************"+beanName+"*************");
            DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager) ApplicationContextHelper
                    .getBeanName(beanName);
            TransactionStatus transactionStatus = dataSourceTransactionManager
                    .getTransaction(new DefaultTransactionDefinition());
            transactionStatuStack.push(transactionStatus);
            dataSourceTransactionManagerStack.push(dataSourceTransactionManager);
        }
        System.out.println("********transactionStatuStack*****"+transactionStatuStack.size()+"*************");
        System.out.println("********dataSourceTransactionManagerStack*****"+dataSourceTransactionManagerStack.size()+"*************");
        return true;
    }

    /**
     * 提交处理方法
     * @param dataSourceTransactionManagerStack
     * @param transactionStatuStack
     */
    private void commit(Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,
                        Stack<TransactionStatus> transactionStatuStack) {
        while (!dataSourceTransactionManagerStack.isEmpty()) {
            System.out.println("*************事务commit*************");
            dataSourceTransactionManagerStack.pop().commit(transactionStatuStack.pop());
        }
    }

    /**
     * 回滚处理方法
     * @param dataSourceTransactionManagerStack
     * @param transactionStatuStack
     */
    private void rollback(Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,
                          Stack<TransactionStatus> transactionStatuStack) {
        while (!dataSourceTransactionManagerStack.isEmpty()) {
            System.out.println("*************事务rollback*************");
            dataSourceTransactionManagerStack.pop().rollback(transactionStatuStack.pop());
        }
    }

}

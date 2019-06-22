package com.example.demo.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 事务组 支持类
 * @author zhangjie
 * @date 2019/6/22 9:34
 */
@Component
public class MultipleTransactionSupport {

    @Autowired
    private DataSourceTransactionManager transactionManagerOne;

    @Autowired
    private DataSourceTransactionManager transactionManagerTwo;

    /**
     * 定义事务组 ，一个事务组主要包含事务管理器和事务状态
     */
    public static class TransactionGroup{
        private DataSourceTransactionManager transactionManager;
        private TransactionStatus transactionStatus;

        public TransactionGroup(DataSourceTransactionManager transactionManager, TransactionStatus transactionStatus) {
            this.transactionManager = transactionManager;
            this.transactionStatus = transactionStatus;
        }
    }

    /**
     * 多事务管理器类：初始化、追加事务组
     */
    public static class MultipleTransactionManager{
        private List<TransactionGroup> transactionGroups = new ArrayList<>(3);

        public MultipleTransactionManager() {
        }

        public void addTransactionGroup(TransactionGroup transactionGroup){
            this.transactionGroups.add(transactionGroup);
        }
    }

    public TransactionGroup getTxGroupOne(){
        return new TransactionGroup(transactionManagerOne,transactionManagerOne.getTransaction(getTxDef()));
    }

    public TransactionGroup getTxGroupTwo(){
        return new TransactionGroup(transactionManagerTwo,transactionManagerTwo.getTransaction(getTxDef()));
    }

    /**
     * 开启多个事务
     * @param transactionGroups
     * @return
     */
    public MultipleTransactionManager beginTransactionMultiple(TransactionGroup... transactionGroups){
        MultipleTransactionManager multipleTransactionManager = new MultipleTransactionManager();
        for(TransactionGroup transactionGroup : transactionGroups){
            multipleTransactionManager.addTransactionGroup(transactionGroup);
        }
        return multipleTransactionManager;
    }

    /**
     * 提交或者回滚多个事务
     * @param multipleTransactionManager
     * @param commitFlag
     */
    public void commitOrRollMultiple(MultipleTransactionManager multipleTransactionManager,boolean commitFlag){
        List<TransactionGroup> transactionGroups = multipleTransactionManager.transactionGroups;
        //事务先开启，后提交，所有要倒叙遍历事务组
        Collections.reverse(transactionGroups);
        for(TransactionGroup transactionGroup : transactionGroups){
            if(commitFlag){
                transactionGroup.transactionManager.commit(transactionGroup.transactionStatus);
            }else{
                transactionGroup.transactionManager.rollback(transactionGroup.transactionStatus);
            }
        }
    }


    /**
     * 事务属性配置
     * getPropagationBehavior() 事务的传播行为 默认0
     *     int PROPAGATION_REQUIRED = 0;   表示当前方法必须运行在事务中。如果当前事务存在，方法将会在该事务中运行。否则，会启动一个新的事务
     *         required , 必须。默认值，A如果有事务，B将使用该事务；如果A没有事务，B将创建一个新的事务。
     *     int PROPAGATION_SUPPORTS = 1;    表示当前方法不需要事务上下文，但是如果存在当前事务的话，那么该方法会在这个事务中运行
     *         supports ，支持。A如果有事务，B将使用该事务；如果A没有事务，B将以非事务执行。
     *     int PROPAGATION_MANDATORY = 2;   表示该方法必须在事务中运行，如果当前事务不存在，则会抛出一个异常
     *         mandatory ，强制。A如果有事务，B将使用该事务；如果A没有事务，B将抛异常。
     *     int PROPAGATION_REQUIRES_NEW = 3; 表示当前方法必须运行在它自己的事务中。一个新的事务将被启动。如果存在当前事务，在该方法执行期间，当前事务会被挂起
     *         requires_new，必须新的。如果A有事务，将A的事务挂起，B创建一个新的事务；如果A没有事务，B创建一个新的事务。
     *     int PROPAGATION_NOT_SUPPORTED = 4;  表示该方法不应该运行在事务中。如果存在当前事务，在该方法运行期间，当前事务将被挂起
     *         not_supported ,不支持。如果A有事务，将A的事务挂起，B将以非事务执行；如果A没有事务，B将以非事务执行。
     *     int PROPAGATION_NEVER = 5;   表示当前方法不应该运行在事务上下文中。如果当前正有一个事务在运行，则会抛出异常
     *         never，从不。如果A有事务，B将抛异常；如果A没有事务，B将以非事务执行。
     *     int PROPAGATION_NESTED = 6; 表示如果当前已经存在一个事务，那么该方法将会在嵌套事务中运行。嵌套的事务可以独立于当前事务进行单独地提交或回滚。
     *                                  如果当前事务不存在，那么其行为与PROPAGATION_REQUIRED一样
     *         nested ，嵌套。A和B底层采用保存点机制，形成嵌套事务。
     *
     * getIsolationLevel(); 事务的隔离级别
     *     int ISOLATION_DEFAULT = -1;   使用后端数据库默认的隔离级别
     *     int ISOLATION_READ_UNCOMMITTED = 1;  最低的隔离级别，允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读
     *     int ISOLATION_READ_COMMITTED = 2;    允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生
     *     int ISOLATION_REPEATABLE_READ = 4;   对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生
     *     int ISOLATION_SERIALIZABLE = 8;      最高的隔离级别，完全服从ACID的隔离级别，确保阻止脏读、不可重复读以及幻读，也是最慢的事务隔离级别，因为它通常是通过完全锁定事务相关的数据库表来实现的
     *
     * getTimeout(); 事务的超时时间
     * isReadOnly(); 事务是否只读
     * String getName(); 事务名称
     */
    private TransactionDefinition getTxDef(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName(this.getClass().getName() + System.currentTimeMillis());
        def.setReadOnly(false);
        return def;
    }



}

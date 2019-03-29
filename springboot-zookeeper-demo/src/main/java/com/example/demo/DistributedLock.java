package com.example.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 通过zookeeper 实现分布式锁
 * @author zhangjie
 * @date 2019/3/18 16:45
 */
public class DistributedLock implements Lock, Watcher {
    /**
     * 基于Zookeeper分布式锁的流程
     * 1.在zookeeper指定节点（locks）下创建临时节点node_n
     * 2.获取locks下的所有子节点children
     * 3.对子节点按节点自增序号从小到大排序
     * 4.判断本节点是否为第一个子节点，若是，则获取锁；若不是，则监听比该节点小的那个节点的删除事件
     * 5.若监听事件生效，则回到第二步重新进行判断，直到获取到锁
     *
     * 具体实现
     * 通过实现Watch接口，实现process（WatchedEvent event）方法来实施监控，使CountDownLatch来完成监控，
     *    在等待锁的时候使用CountDownLatch来计数，等到后进行countDown，停止等到，继续运行。
     */

    private ZooKeeper zk = null;
    //根节点
    private String ROOT_LOCK = "/locks";
    //竞争的资源
    private String lockName;
    //等待的前一个锁
    private String WAIT_LOCK;
    //当前锁
    private String CURRENT_LOCK;
    //计数器
    private CountDownLatch countDownLatch;

    private static final int SESSION_TIMEOUT = 30 * 1000;

    private List<Exception> exceptionList = new ArrayList<Exception>();

    //初始化zookeeper实例
    private void createZKInstance() throws IOException {
        //连接到ZK服务，多个可以用逗号分割
        zk = new ZooKeeper("127.0.0.1:2181",SESSION_TIMEOUT,this);
    }

    /**
     * 配置分布式锁
     * @param lockName 竞争的资源 锁名称
     */
    public DistributedLock(String lockName){
        this.lockName = lockName;
        try {
            //初始化zookeeper实例
            zk = new ZooKeeper("127.0.0.1:2181",SESSION_TIMEOUT,this);
            Stat stat = zk.exists(ROOT_LOCK,false);
            if(stat == null){
                //如果根节点不存在，则创建根节点(完全开放)
                zk.create(ROOT_LOCK,new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }

        } catch (IOException e) {
            //e.printStackTrace();
            exceptionList.add(e);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            exceptionList.add(e);
        } catch (KeeperException e) {
            //e.printStackTrace();
            exceptionList.add(e);
        }
    }

    /**
     * 节点监视器
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(this.countDownLatch != null){
            System.out.println(watchedEvent.toString()+"；监视节点");
            this.countDownLatch.countDown();
        }
    }

    @Override
    public void lock() {
        if(exceptionList.size() > 0){
            throw new LockException(exceptionList.get(0));
        }
        try {
            if(this.tryLock()){
                System.out.println(Thread.currentThread().getName() + " " + lockName + "获得了锁: "+CURRENT_LOCK);
                return;
            }else{
                //等待锁
                waitForLock(WAIT_LOCK,SESSION_TIMEOUT);
            }
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        }

    }

    @Override
    public boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if(lockName.contains(splitStr)){
                throw new LockException("锁名有误");
            }
            //创建临时有序节点
            CURRENT_LOCK = zk.create(ROOT_LOCK+"/"+lockName+splitStr,new byte[0],ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName()+": "+CURRENT_LOCK +"已创建");
            //获取所有子节点
            List<String> subNodes = zk.getChildren(ROOT_LOCK,false);
            //取出所有lockName的锁
            List<String> locks = new ArrayList<>();
            for(String subNode : subNodes){
                String node = subNode.split(splitStr)[0];
                if(node.equals(lockName)){
                    locks.add(subNode);
                }
            }
            Collections.sort(locks);
            System.out.println("tryLock: " + Thread.currentThread().getName() +"的锁是"+ CURRENT_LOCK);
            //若当前节点为最小节点，则获取锁成功
            if(CURRENT_LOCK.equals(ROOT_LOCK + "/" + locks.get(0))){
                return true;
            }
            //若不是最小节点，则找到自己的前一个节点
            String preNode = CURRENT_LOCK.substring(CURRENT_LOCK.lastIndexOf("/") + 1);
            WAIT_LOCK = locks.get(Collections.binarySearch(locks,preNode) - 1);
            System.out.println(Thread.currentThread().getName()+"-"+CURRENT_LOCK+"-"+preNode+"-"+WAIT_LOCK);
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        }
        return false;
    }

    //等待锁
    private boolean waitForLock(String prev, long waitTime) throws KeeperException, InterruptedException {
        Stat stat = zk.exists(ROOT_LOCK + "/" +prev,true);
        if(stat != null){
            System.out.println(Thread.currentThread().getName() + "等待锁" + ROOT_LOCK + "/" + prev);
            this.countDownLatch = new CountDownLatch(1);
            //计数等待，若等到前一个节点消失，则process中进行countDown,停止等待，获取锁
            this.countDownLatch.await(waitTime,TimeUnit.MILLISECONDS);
            this.countDownLatch = null;
            System.out.println("waitForLock: "+ Thread.currentThread().getName() + "等到了锁:"+CURRENT_LOCK);
        }
        return  true;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        this.lock();
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        try {
            if(this.tryLock()){
                return true;
            }
            return waitForLock(WAIT_LOCK,time);
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void unlock() {
        System.out.println("unlock:"+Thread.currentThread().getName() + "释放锁:" + CURRENT_LOCK);
        try {
            zk.delete(CURRENT_LOCK,-1);
            CURRENT_LOCK = null;
            zk.close();
        } catch (InterruptedException e) {
            throw new LockException(e);
        } catch (KeeperException e) {
            throw new LockException(e);
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public class LockException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public LockException(String e){
            super(e);
        }
        public LockException(Exception e){
            super(e);
        }
    }

}

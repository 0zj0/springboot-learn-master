package com.example.demo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @author zhangjie
 * @date 2019/3/15 17:53
 */
public class ZookeeperTest implements Watcher {

    //创建zookeeper实例
    private ZooKeeper zk;

    private static final int SESSION_TIMEOUT = 30 * 1000;

    //1.初始化zookeeper实例
    public ZookeeperTest() throws IOException {
        zk = new ZooKeeper("127.0.0.1:2181",SESSION_TIMEOUT,this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.toString()+"；监视节点");
    }

    //2.查询子节点
    public void getChildren(String path) throws KeeperException, InterruptedException {
        List<String> childs = zk.getChildren(path,true);
        if(childs==null || childs.size() == 0){
            System.out.println("无子节点数据");
        }
        childs.forEach(System.out::println);
    }

    //3.删除子节点
    public void deleteChildren(String path) throws KeeperException, InterruptedException {
        zk.delete(path,-1);
    }

    //4.关闭zookeeper连接
    public void closeZk() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZookeeperTest test = new ZookeeperTest();
        test.getChildren("/locks");
        test.closeZk();
    }
}

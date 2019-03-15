package com.example.demo;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author zhangjie
 * @date 2019/3/15 17:53
 */
public class ZookeeperClusterTest {

    //回话超时，设置为与系统默认时间一致
    private static final int SESSION_TIMEOUT = 30 * 1000;

    private static final String PATH = "/test";

    //创建zookeeper实例
    private ZooKeeper zk;

    //创建Watch实例（监视器）
    private Watcher watcher = new Watcher() {

        //监视事件
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("WatchedEvent >> " + watchedEvent.toString());
        }
    };

    //初始化zookeeper实例
    private void createZKInstance() throws IOException {
        //连接到ZK服务，多个可以用逗号分割
        zk = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",SESSION_TIMEOUT,this.watcher);
    }

    private void ZKOperations() throws KeeperException, InterruptedException {
        //权限：
        // OPEN_ACL_UNSAFE（完全开放），
        // CREATOR_ALL_ACL：创建该znode的连接拥有所有权限，
        // READ_ACL_UNSAFE：所有的客户端都可读 ，
        // 节点类型： Persistent（持久）
        zk.create(PATH,"zj001".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //查看 stat:此为状态信息, 描述该Znode的版本, 权限等信息
        System.out.println(new String(zk.getData(PATH,this.watcher,null)));     //返回zj001

        //修改
        //WatchedEvent >>> WatchedEvent state:SyncConnected type:NodeDataChanged path:/test
        zk.setData(PATH,"zj002".getBytes(),-1);

        // 这里再次进行修改，则不会触发Watch事件，这就是我们验证ZK的一个特性“一次性触发”
        zk.setData(PATH, "zj003".getBytes(), -1);

        //查看数据
        System.out.println(new String(zk.getData(PATH,false,null)));

        //删除
        zk.delete(PATH,-1);

        //查看是否删除
        System.out.println(" 节点状态： [" + zk.exists(PATH, false) + "]");  // 节点状态： [null]
    }

    //关闭连接
    private void ZKClose() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZookeeperClusterTest test = new ZookeeperClusterTest();
        test.createZKInstance();
        test.ZKOperations();
        test.ZKClose();
    }

}

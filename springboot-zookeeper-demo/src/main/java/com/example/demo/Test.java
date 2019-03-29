package com.example.demo;

/**
 * @author zhangjie
 * @date 2019/3/19 11:38
 */
public class Test {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DistributedLock distributedLock = null;
                try {
                    distributedLock = new DistributedLock("test1");
                    distributedLock.lock();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(distributedLock != null ){
                        distributedLock.unlock();
                    }
                }
            }
        };

        for(int i = 0 ; i < 10 ; i++){
            Thread t = new Thread(runnable);
            t.start();
        }
    }

}

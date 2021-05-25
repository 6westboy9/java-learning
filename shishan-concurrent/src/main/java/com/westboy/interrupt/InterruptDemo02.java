package com.westboy.interrupt;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 参考：https://www.jianshu.com/p/1e1276749338
 */
public class InterruptDemo02 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();

        // 开启线程，并获取锁，线程 1 持有锁
        Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            System.out.println("t1：打印数据");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        });
        t1.start();

        Thread.sleep(100);

        Thread t2 = new Thread(() -> {
            // 线程加入到 sync queue 中，即线程被阻塞
            reentrantLock.lock();
            System.out.println("t2：打印数据");
            if (Thread.interrupted()) {
                System.out.println("t2：线程被中断过");
            }
            reentrantLock.unlock();
        });
        t2.start();
        Thread.sleep(100);

        t2.interrupt();


        System.out.println("主线程");
    }
}

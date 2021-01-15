package com.westboy.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pengbo
 * @since 2021/1/13
 */
public class ReentrantLockDemo {

    private static int i = 0;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final CountDownLatch LATCH = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        // 启动两个线程
        new Thread(new Worker()).start();
        new Thread(new Worker()).start();

        LATCH.await();

        System.out.println(i);
    }


    static class Worker implements Runnable {
        @Override
        public void run() {
            LOCK.lock();
            try {
                for (int j = 0; j < 1000; j++) {
                    i++;
                }
                LATCH.countDown();
            } finally {
                LOCK.unlock();
            }
        }
    }
}

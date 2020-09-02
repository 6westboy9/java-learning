package com.westboy.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Worker());
        thread.start();

        // 保证线程已经启动，并且已经处于 WAITING 状态
        TimeUnit.SECONDS.sleep(1);

        // 不加 thread.interrupt() 会一直等待，直到线程被唤醒
        thread.interrupt();

    }


    static class Worker implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running...");
            // LockSupport.park();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


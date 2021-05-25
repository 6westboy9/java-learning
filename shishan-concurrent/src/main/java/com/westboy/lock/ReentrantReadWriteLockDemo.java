package com.westboy.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author pengbo
 * @since 2021/1/13
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                readLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " 休眠 30 秒...");
                    latch.countDown();
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }).start();
        }


        latch.await();

        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 休眠 30 秒...");
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }


        // readLock.unlock();

        writeLock.lock();
        writeLock.unlock();
    }
}

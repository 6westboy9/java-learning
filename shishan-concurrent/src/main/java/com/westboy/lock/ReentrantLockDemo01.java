package com.westboy.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo01 {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 等待获取锁");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 获取锁成功");
            try {
                System.out.println(Thread.currentThread().getName() + " 线程开始执行任务");
                TimeUnit.MINUTES.sleep(10);
                System.out.println(Thread.currentThread().getName() + " 线程结束执行任务");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放锁成功");
            }
        }, "Thread-1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 等待获取锁");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 获取锁成功");
            try {
                System.out.println(Thread.currentThread().getName() + " 线程开始执行任务");
                TimeUnit.MINUTES.sleep(10);
                System.out.println(Thread.currentThread().getName() + " 线程结束执行任务");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放锁成功");
            }
        }, "Thread-2").start();

    }
}

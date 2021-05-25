package com.westboy.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pengbo
 * @since 2021/1/13
 */
public class ReentrantLockDemo02 {

    private final ReentrantLock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo02 demo = new ReentrantLockDemo02();

        new Thread(demo::awaitA, "Thread-A").start();
        new Thread(demo::awaitB, "Thread-B").start();

        Thread.sleep(2000);
        demo.signalA();

        Thread.sleep(2000);
        demo.signalB();
    }


    public void awaitA() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 进入了 awaitA 方法");
            long timeBefore = System.currentTimeMillis();

            conditionA.await();

            long timeAfter = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " 被唤醒");
            System.out.println(Thread.currentThread().getName() + " 等待了: " + (timeAfter - timeBefore) / 1000 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 进入了 awaitB 方法");
            long timeBefore = System.currentTimeMillis();

            conditionB.await();

            long timeAfter = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " 被唤醒");
            System.out.println(Thread.currentThread().getName() + " 等待了: " + (timeAfter - timeBefore) / 1000 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalA() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 启动唤醒 Thread-A 程序");
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalB() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 启动唤醒 Thread-B 程序");
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

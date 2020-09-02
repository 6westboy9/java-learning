package com.westboy.concurrent;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {

        // ReentrantLock lock = new ReentrantLock();
        // 测一下如何响应中断的
        // lock.lock();
        // lock.unlock();

        // LockTest test = new LockTest();
        // try {
        //     test.throwException();
        // } catch (InterruptedException e) {
        //     System.out.println("线程中断了");
        // } finally {
        //     System.out.println("finally");
        // }

        List<Thread> threadList = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            Thread thread = new Thread(new Worker());
            threadList.add(thread);
            thread.start();
        }

        // try {
        //     // 等待所有线程都起来
        //     TimeUnit.SECONDS.sleep(1);
        // } catch (InterruptedException e) {
        //     System.out.println("当前线程: " + Thread.currentThread().getName() + " 响应中断");
        //     e.printStackTrace();
        // }

        // 中断 Thread-2 线程，此时 Thread-2 大几率处于阻塞状态，等待已经成功获取锁的线程节点释放锁并唤醒后继节点
        // threadList.get(1).interrupt();


    }

    private static final ReentrantLock lock = new ReentrantLock();
    // private static int i = 1;

    static class Worker implements Runnable {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            try {
                lock.lockInterruptibly();
                // lock.lock();
                // 一个耗时的计算过程
                long j = 0;
                for (; j < 10000000000L; j++) {
                    // System.out.println("当前线程: " + Thread.currentThread().getName() + ", j: " + j + ", i: " + i);
                    // i++;
                    // TimeUnit.SECONDS.sleep(1);
                }
                System.out.println("当前线程: " + threadName + ", j: " + j);

            } catch (InterruptedException e) {
                System.out.println("当前线程: " + threadName + " 响应中断");
                e.printStackTrace();
            } finally {
                System.out.println("当前线程: " + threadName + " 获取所状态: " + lock.isHeldByCurrentThread());
                if (lock.isHeldByCurrentThread()) {
                    System.out.println("当前线程: " + threadName + " 释放锁");
                    lock.unlock();
                }
            }
        }
    }
}

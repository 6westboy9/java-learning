package com.westboy.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间通信
 *
 * @author pengbo
 * @since 2021/1/12
 */
public class ReentrantLockConditionDemo {

    private static final Map<String, String> messages = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(1);
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(new Employee()).start();
        new Thread(new Employer()).start();

        // kill -9 PID 不能执行
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("哈哈哈哈")));
    }

    static class Employee implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                // System.out.println("雇佣:获取锁");
                try {
                    while (messages.isEmpty()) {
                        System.out.println("雇佣:等待消息");
                        condition.await();
                        System.out.println("雇佣:获取到消息");
                    }
                    for (Map.Entry<String, String> entry : messages.entrySet()) {
                        System.out.println("雇佣:处理消息:" + entry.getKey() + ", 消息体:" + entry.getValue());
                        messages.remove(entry.getKey());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // System.out.println("雇佣:释放锁");
                    lock.unlock();
                }
            }
        }
    }

    static class Employer implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                // System.out.println("雇主:获取锁");
                try {
                    int count = counter.getAndIncrement();
                    System.out.println("雇主:投递任务");
                    messages.put("任务ID:" + count, "任务代号:" + count);
                    condition.signalAll();
                } finally {
                    // System.out.println("雇主:释放锁");
                    lock.unlock();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

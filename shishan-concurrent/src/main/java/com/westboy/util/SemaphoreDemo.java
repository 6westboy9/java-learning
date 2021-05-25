package com.westboy.util;

import java.util.concurrent.Semaphore;

/**
 * @author pengbo
 * @since 2021/1/9
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        demo1();
        // demo2();
    }

    private static void demo1() {
        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 执行开始...");
                try {
                    semaphore.acquire(1);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(1);
                    // semaphore.release(); 默认 = semaphore.release(1)
                }
                System.out.println(Thread.currentThread().getName() + " 执行完成...");
            }).start();
        }
    }

    private static void demo2() throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 执行开始...");
            try {
                Thread.sleep(5);
                System.out.println(Thread.currentThread().getName() + " 执行完成...");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("等待异步任务执行完成...");
        semaphore.acquire();
        System.out.println("异步任务执行完成...");
    }

}

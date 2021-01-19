package com.westboy.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengbo
 * @since 2021/1/9
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 一个线程等待其他多个线程都执行完毕，再继续自己的工作
        demo1();
        System.out.println("-----------华丽的分割线-----------");
        // 多个线程等待某一个线程的信号，同时开始执行
        demo2();
    }

    public static void demo1() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = () -> {
                try {
                    long time = (long) (Math.random() * 1000);
                    Thread.sleep(time);
                    System.out.println(Thread.currentThread().getName() + " " + no + " 号运动员完成了比赛，耗时 " + time + " 毫秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            };
            service.submit(runnable);
        }
        System.out.println("等待 5 个运动员都跑完...");
        latch.await();
        System.out.println("比赛结束");
        service.shutdown();
    }

    private static void demo2() throws InterruptedException {
        System.out.println("运动员有 5 秒的准备时间");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = () -> {
                System.out.println(Thread.currentThread().getName() + " " + no + " 号运动员准备完毕，等待裁判员的发令枪");
                try {
                    countDownLatch.await();
                    System.out.println(no + " 号运动员开始跑步了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("5 秒准备时间已过，发令枪响，比赛开始！");
        countDownLatch.countDown();
        service.shutdown();
    }

}

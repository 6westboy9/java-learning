package com.westboy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author pengbo
 * @since 2021/1/10
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {

        // 使用线程池工具类创建固定线程数线程池
        ExecutorService executorService1 = Executors.newFixedThreadPool(3);
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        // executorService.submit(() -> System.out.println("你好！"));


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("你好！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 自己手工定制
        // ThreadPoolExecutor executor = new ThreadPoolExecutor();

    }
}

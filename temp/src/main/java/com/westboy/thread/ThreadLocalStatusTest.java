package com.westboy.thread;

import java.util.concurrent.*;

/**
 * @author pengbo
 * @since 2020/10/12
 */
public class ThreadLocalStatusTest {

    public static void main(String[] args) {
        // ExecutorService executor = Executors.newSingleThreadExecutor();
        // executor.submit(() -> System.out.println("Hello World!"));


        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);

        // System.out.println(workQueue.isEmpty());

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, workQueue);
        executor.submit(() -> System.out.println("Hello World!"));
        System.out.println(executor.isShutdown());
        System.out.println(executor.isTerminated());
        System.out.println(executor.isTerminating());
    }
}

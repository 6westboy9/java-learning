package com.westboy.thread;

import java.util.concurrent.*;

/**
 * @author pengbo
 * @since 2020/10/14
 */
public class ThreadPoolExceptionTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> result = executorService.submit(() -> {
            System.out.println(1 / 0);
            return "ok";
        });

        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

package com.westboy.temp;

import cn.hutool.core.thread.ThreadUtil;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadDemo01 {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        executorService.schedule(() -> {
            System.out.println("haha");
            countDownLatch.countDown();
        }, 10, TimeUnit.SECONDS);


        countDownLatch.await();
        executorService.shutdown();
        // while (true) {
        //     Thread.sleep(1000);
        //     System.out.println(LocalDateTime.now());
        // }
    }
}

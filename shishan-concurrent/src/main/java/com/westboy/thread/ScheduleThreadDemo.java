package com.westboy.thread;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author pengbo
 * @since 2021/1/19
 */
public class ScheduleThreadDemo {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2, new ThreadFactoryBuilder().setNamePrefix("SendRedPacket").build());

        for (int i = 0; i < 10; i++) {
            // service.schedule(() -> {
            //     System.out.println(Thread.currentThread().getName() + "Hello");
            // }, 3, TimeUnit.SECONDS);

            service.scheduleWithFixedDelay(() -> {
                System.out.println(Thread.currentThread().getName() + "Hello");
            }, 0, 3, TimeUnit.SECONDS);
        }
    }
}

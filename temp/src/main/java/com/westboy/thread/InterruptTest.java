package com.westboy.thread;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pengbo
 * @since 2020/10/12
 */
public class InterruptTest {


    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);

        List<Task> tasks = new ArrayList<>(100);

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);


        // ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, workQueue);
        ThreadPoolExecutor executor = new MyThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, workQueue, new ThreadFactoryBuilder().setNameFormat("Task-%d").build());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executor.shutdown();
            if (executor.isShutdown()) {
                String metric = "CorePoolSize:" + executor.getCorePoolSize() +
                        ", PoolSize:" + executor.getPoolSize() +
                        ", LargestPoolSize:" + executor.getLargestPoolSize() +
                        ", MaximumPoolSize:" + executor.getMaximumPoolSize() +
                        ", ActiveCount:" + executor.getActiveCount() +
                        ", CompletedTaskCount:" + executor.getCompletedTaskCount() +
                        ", TaskCount:" + executor.getTaskCount();
                System.out.println(metric);
                int num = 1;
                for (Task task : tasks) {
                    System.out.println((num++) + "\tTaskID:" + task.getTaskId() + "\t -> Finish:" + task.getStatus());
                }
            }
        }));

        // monitor(executor);

        for (int i = 1; i <= 100; i++) {
            int finalIndex = i;
            executor.submit(() -> {
                Task task = new Task(finalIndex, 0);
                tasks.add(task);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.setStatus(1);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.setStatus(2);
            });
        }
    }

    private static void monitor(ThreadPoolExecutor executor) {
        File file = FileUtil.newFile("metric.txt");
        if (file.delete()) {
            file = FileUtil.newFile("metric.txt");
        }

        File finalFile = file;
        Thread thread = new Thread(() -> {
            while (true) {
                String metric = "CorePoolSize:" + executor.getCorePoolSize() +
                        ", PoolSize:" + executor.getPoolSize() +
                        ", LargestPoolSize:" + executor.getLargestPoolSize() +
                        ", MaximumPoolSize:" + executor.getMaximumPoolSize() +
                        ", ActiveCount:" + executor.getActiveCount() +
                        ", CompletedTaskCount:" + executor.getCompletedTaskCount() +
                        ", TaskCount:" + executor.getTaskCount();

                FileUtil.appendUtf8Lines(CollUtil.newArrayList(metric), finalFile);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    @Data
    @AllArgsConstructor
    static class Task {
        private Integer taskId;
        private Integer status;
    }
}

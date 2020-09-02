package com.westboy.thread;

import java.util.concurrent.TimeUnit;

public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1111111111111111");
        });

        // 1. 虚拟机中可能同时有多个线程运行，只有当所有的非守护线程（通常都是用户线程）都结束的时候，虚拟机的进程才会结束，不管当前运行的线程是不是 main 线程。
        // 2. main 线程运行结束，如果此时运行的其他线程全部是 Daemon 线程，JVM 会使这些线程停止，同时退出。但是如果此时正在运行的其他线程有非守护线程，那么必须等所有的非守护线程结束，JVM 才会退出。
        thread.setDaemon(true);
        thread.start();
    }
}

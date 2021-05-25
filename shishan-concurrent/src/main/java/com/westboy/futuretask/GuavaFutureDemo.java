package com.westboy.futuretask;

import cn.hutool.core.lang.Console;
import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaFutureDemo {
    public static final int SLEEP_GAP = 500;

    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean> {
        @Override
        public Boolean call() {
            try {
                Console.log(getCurThreadName() + " 洗好水壶");
                Console.log(getCurThreadName() + " 灌上凉水");
                Console.log(getCurThreadName() + " 放在火上");
                Thread.sleep(SLEEP_GAP);
                Console.log(getCurThreadName() + " 水开了");
            } catch (InterruptedException e) {
                Console.error(getCurThreadName() + " 烧水工作发生异常被中断");
                return false;
            }
            Console.log(getCurThreadName() + " 烧水工作运行结束");
            return true;
        }
    }

    static class WashJob implements Callable<Boolean> {
        @Override
        public Boolean call() {
            try {
                Console.log(getCurThreadName() + " 洗茶壶");
                Console.log(getCurThreadName() + " 洗茶叶");
                Console.log(getCurThreadName() + " 拿茶叶");
                Thread.sleep(SLEEP_GAP);
                Console.log(getCurThreadName() + " 洗完了");
            } catch (InterruptedException e) {
                Console.error(getCurThreadName() + " 清晰工作发生异常被中断");
                return false;
            }
            Console.log(getCurThreadName() + " 清晰工作运行结束");
            return true;
        }
    }

    //新创建一个异步业务类型，作为泡茶喝主线程类
    static class MainJob implements Runnable {
        boolean waterOk = false;
        boolean cupOk = false;

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(SLEEP_GAP / 10);
                    Console.log(getCurThreadName() + " 读书中......");
                } catch (InterruptedException e) {
                    Console.error(getCurThreadName() + " 发生异常被中断");
                }

                drinkTea(waterOk, cupOk);
            }
        }

        public void drinkTea(Boolean wOk, Boolean cOK) {
            if (wOk && cOK) {
                Console.log(getCurThreadName() + " 泡茶喝，茶喝完");
            } else if (!wOk) {
                Console.log(getCurThreadName() + " 烧水失败，没有茶喝了");
            } else {
                Console.log(getCurThreadName() + " 杯子洗不了，没有茶喝了");
            }
        }
    }

    public static void main(String[] args) {
        MainJob mainJob = new MainJob();
        Thread mainThread = new Thread(mainJob);
        mainThread.start();

        Callable<Boolean> hotJob = new HotWaterJob();
        Callable<Boolean> washJob = new WashJob();

        // 创建 Java 线程池
        ExecutorService jPool = Executors.newFixedThreadPool(10);
        // 包装 Java 线程池，构造 Guava 线程池
        ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool);

        // 提交烧水的业务逻辑实例，到 Guava 线程池获取异步任务
        ListenableFuture<Boolean> hotFuture = gPool.submit(hotJob);
        // 绑定异步回调，烧水完成后，把喝水任务的 waterOk 标志设置为 true
        Futures.addCallback(hotFuture, new FutureCallback<Boolean>() {
            public void onSuccess(Boolean r) {
                if (r) {
                    mainJob.waterOk = true;
                }
            }

            public void onFailure(Throwable t) {
                Console.log(getCurThreadName() + " 烧水失败，没有茶喝了");
            }
        }, gPool);

        // 提交清洗的业务逻辑实例，到 Guava 线程池获取异步任务
        ListenableFuture<Boolean> washFuture = gPool.submit(washJob);
        // 绑定异步回调，烧水完成后，把清洗任务的 cupOk 标志设置为 true
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            public void onSuccess(Boolean r) {
                if (r) {
                    mainJob.cupOk = true;
                }
            }
            public void onFailure(Throwable t) {
                Console.log(getCurThreadName() + " 杯子洗不了，没有茶喝了");
            }
        }, gPool);

    }
}

package com.westboy.thread;

import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pengbo
 * @since 2020/10/12
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    public MyThreadPoolExecutor(int corePoolSize,
                                int maximumPoolSize,
                                long keepAliveTime,
                                TimeUnit unit,
                                BlockingQueue<Runnable> workQueue,
                                ThreadFactory threadFactory) {
        // 通过父类构造方法实例化
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("当前线程:" + t.getName() + " 正准备执行任务...");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        int i = atomicInteger.addAndGet(1);
        System.out.println("当前线程:" + Thread.currentThread().getName() + " 执行任务结束..." + "\t i=" + i);
    }

    @Override
    protected void terminated() {
        String metric = "CorePoolSize:" + getCorePoolSize() +
                ", PoolSize:" + getPoolSize() +
                ", LargestPoolSize:" + getLargestPoolSize() +
                ", MaximumPoolSize:" + getMaximumPoolSize() +
                ", ActiveCount:" + getActiveCount() +
                ", CompletedTaskCount:" + getCompletedTaskCount() +
                ", TaskCount:" + getTaskCount();
        System.out.println("From MyThreadPoolExecutor:" + metric);
    }
}

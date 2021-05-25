package com.westboy.interrupt;

import java.util.concurrent.locks.LockSupport;

/**
 * 参考：https://www.jianshu.com/p/1e1276749338
 */
public class InterruptDemo01 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("park 开始");
            LockSupport.park();
            System.out.println("park 结束");
        });

        // 线程挂起
        t1.start();
        // 中断 t1
        // t1.interrupt();
        LockSupport.unpark(t1);
        System.out.println("是否被中断：" + t1.isInterrupted());
    }
}

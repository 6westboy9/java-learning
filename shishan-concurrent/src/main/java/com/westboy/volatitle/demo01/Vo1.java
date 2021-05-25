package com.westboy.volatitle.demo01;

import java.util.concurrent.CountDownLatch;

public class Vo1 {

    boolean run = true;
    volatile int s = 1;

    public static void main(String[] args) throws InterruptedException {
        // for (int i = 0; i < 1; i++) {
            test();
        // }
    }

    private static void test() throws InterruptedException {
        Vo1 v = new Vo1();
        CountDownLatch latch = new CountDownLatch(1);

        // thread1
        new Thread(()->{
            while (v.run) {
                // 打开下面任意一行代码都可以终止，但是当注释掉下面两行代码时，将一直循环执行
                // int a = v.s;
                // System.out.println("haha");
            }

            latch.countDown();
        }).start();

        // thread2
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            v.run = false;
        }).start();

        latch.await();
    }
}

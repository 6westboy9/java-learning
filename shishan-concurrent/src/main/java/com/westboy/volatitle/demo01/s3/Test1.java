package com.westboy.volatitle.demo01.s3;

/**
 * 实验三
 */
public class Test1 {
    public volatile int i = 0;
    long a1, a2, a3, a4, a5, a6, a7, a8, a9 = 0;
    public int flag = 1;

    public static void main(String[] args) throws InterruptedException {
        Test1 test1 = new Test1();
        Thread a = new Thread(() -> {
            try {
                Thread.sleep(1000);
                test1.flag = 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread b = new Thread(() -> {
            while (test1.flag != 0) {
                test1.i++;
            }
        });
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println("end, i=" + test1.i);
    }
}


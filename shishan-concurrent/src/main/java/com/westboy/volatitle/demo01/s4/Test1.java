package com.westboy.volatitle.demo01.s4;

/**
 * 实验四
 */
public class Test1 {
    public volatile int i = 0;
    long[] a1 = new long[10000000];
    public int flag = 1;

    public static void main(String[] args) throws InterruptedException {
        Test1 test1 = new Test1();
        Thread a = new Thread(() -> {
            try {
                Thread.sleep(100);
                test1.flag = 0;
                for (int i = 0; i < 10000000; i++) {
                    test1.a1[i] = i;
                }

            } catch (Exception e) {
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


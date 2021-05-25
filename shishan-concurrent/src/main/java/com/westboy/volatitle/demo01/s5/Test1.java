package com.westboy.volatitle.demo01.s5;

/**
 * 实验五
 */
public class Test1 {
    public int i = 0;
    public int flag = 1;

    public static void main(String[] args) throws InterruptedException {
        Test1 test1 = new Test1();
        Test3 test3 = new Test3();

        Thread a = new Thread(() -> {
            try {
                Thread.sleep(100);
                test1.flag = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        Thread b = new Thread(() -> {
            while (test1.flag != 0) {
                test3.i++;
            }
        });
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println("end, i=" + test3.i);
    }
}




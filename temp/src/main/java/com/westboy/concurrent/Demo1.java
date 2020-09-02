package com.westboy.concurrent;

import lombok.SneakyThrows;

public class Demo1 {

    private static int num = 0;

    @SneakyThrows
    public static void main(String[] args) {

        // for (int i = 0; i < 1000; i++) {
        //     new Thread(new OrderAsync()).start();
        // }
        //
        // Thread.sleep(3000);
        // System.out.println(num);

        String str1 = new String("a");
        String str2 = new String("a");
        System.out.println(str1.equals(str2));
        System.out.println(str1 == str2);
    }

   static class OrderAsync implements Runnable {
        @Override
        public void run() {
            String orderId = new String("1111111111");
            synchronized (orderId) {
                num++;
            }
        }
    }
}

package com.westboy.concurrent;

import lombok.SneakyThrows;

public class VolatileAtomicTest {

    public static volatile int num = 0;

    // 如果不加 synchronized 就会导致小于 10 * 1000 即 10000
    public static synchronized void increase() {
        num++;
    }

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            }).start();
        }

        Thread.sleep(3000);
        System.out.println(num);
    }
}

package com.westboy.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    public static volatile boolean initFlag = false;

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(() -> {
            while (!initFlag) {
            }
            System.out.println("write success...");
        }).start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            System.out.println("read init...");
            initFlag = true;
            System.out.println("read success...");
        }).start();

        TimeUnit.SECONDS.sleep(2);
    }
}

package com.westboy.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(0);

        for (int i = 0; i < 10000; i++) {
            new Thread(integer::incrementAndGet).start();
        }


        System.out.println(integer.get());

    }
}

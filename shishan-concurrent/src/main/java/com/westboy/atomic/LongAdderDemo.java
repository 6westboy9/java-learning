package com.westboy.atomic;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();

        for (int i = 0; i < 10000; i++) {
            new Thread(longAdder::increment).start();
        }

        System.out.println(longAdder.longValue());

    }
}

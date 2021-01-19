package com.westboy.week_11;

// -XX:ThreadStackSize=1MB
public class Demo1 {

    public static long counter = 0;

    public static void main(String[] args) {
        work();
    }

    private static void work() {
        System.out.println("目前是第" + (++counter) + "次调用方法");
        work();
    }
}

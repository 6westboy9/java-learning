package com.westboy.week_11;

/**
 * 手动模拟 Java 虚拟机栈 OOM
 * <p>
 * -XX:ThreadStackSize=1m
 *
 * <p>
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * -XX:ThreadStackSize=1m
 * -XX:+PrintGCDetails -Xloggc:/Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm-gc-log/week_11_demo_02.log
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm-gc-log/
 */
public class Week11Demo02 {

    public static long counter = 0;

    public static void main(String[] args) {
        work();
    }

    private static void work() {
        System.out.println("目前是第" + (++counter) + "次调用方法");
        work();
    }
}

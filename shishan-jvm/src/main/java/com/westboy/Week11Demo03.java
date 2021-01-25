package com.westboy;

import java.util.ArrayList;
import java.util.List;

/**
 * 手动模拟堆内存 OOM
 *
 * <p>
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * -Xms10m -Xmx10m
 * -XX:+PrintGCDetails -Xloggc:/Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm-gc-log/week_11_demo_03.log
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm-gc-log/
 */
public class Week11Demo03 {
    public static void main(String[] args) {
        long counter = 0;
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
            System.out.println("当前创建了第" + (++counter) + "个对象");
        }
    }

}

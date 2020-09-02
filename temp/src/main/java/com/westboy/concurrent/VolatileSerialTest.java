package com.westboy.concurrent;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class VolatileSerialTest {
    static  int x = 0;
    static  int y = 0;

    @SneakyThrows
    public static void main(String[] args) {
        Set<String> resultSet = new HashSet<>();
        Map<String, Integer> resultMap = new ConcurrentHashMap<>();


        for (int i = 0; i < 10000000; i++) {
            x = 0;
            y = 0;

            Thread one = new Thread(() -> {
                int a = y; // ①
                x = 1;     // ② 上下两条指令可能发生重排序
                resultMap.put("a", a);
            });

            Thread two = new Thread(() -> {
                int b = x; // ①
                y = 1;     // ② 上下两条指令可能发生重排序
                resultMap.put("b", b);
            });

            one.start();
            two.start();
            one.join();
            two.join();

            // 组合情况有：[{a=1, b=0}, {a=1, b=1}, {a=0, b=0}, {a=0, b=1}]
            // 如果不加 volatile 就会有 {a=0, b=0} 出现，此情况的出现为指令重排序导致

            resultSet.add("{a=" + resultMap.get("a") + ", b=" + resultMap.get("b") + "}");

            System.out.println(resultSet);
        }


    }

}

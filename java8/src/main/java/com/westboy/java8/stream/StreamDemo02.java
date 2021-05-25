package com.westboy.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class StreamDemo02 {
    public static void main(String[] args) {
        // stream();
        // parallelStream();
        // 对比测试
        test();
    }

    private static void stream() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(StreamDemo02::evaluate)
                .ifPresent(System.out::println);
    }

    private static Integer evaluate(Integer a, Integer b) {
        System.out.printf("%s: %d + %d = %d%n", Thread.currentThread().getName(), a, b, a + b);
        return a + b;
    }

    private static void parallelStream() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallel()
                .reduce(StreamDemo02::evaluate)
                .ifPresent(System.out::println);
    }

    private static void test() {
        System.out.printf("本计算机的核数：%d%n", Runtime.getRuntime().availableProcessors());

        // 产生1000w个随机数(1 ~ 100)，组成列表
        Random random = new Random();
        List<Integer> list = new ArrayList<>(100_000_000);

        for (int i = 0; i < 100_000_000; i++) {
            list.add(random.nextInt(100));
        }

        long prevTime = getCurrentTime();
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        System.out.println(sum);
        System.out.printf("单线程计算耗时：%d%n", getCurrentTime() - prevTime);


        prevTime = getCurrentTime();
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);
        System.out.printf("单线程计算耗时：%d%n", getCurrentTime() - prevTime);

        prevTime = getCurrentTime();
        list.stream().parallel().reduce(Integer::sum).ifPresent(System.out::println);
        System.out.printf("多线程计算耗时：%d%n", getCurrentTime() - prevTime);
    }

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }
}

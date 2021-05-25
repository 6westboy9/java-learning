package com.westboy.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ArrayBlockingQueueDemo {

    private static final ArrayBlockingQueue<Integer> QUEUE = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    // put 操作会阻塞，offer 不会阻塞，add 不会阻塞，add 和 offer 的唯一区别就是在队列满时，再添加元素，offer 返回 false，add 直接抛异常
                    QUEUE.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("添加：" + i);
            }
        }).start();

        new Thread(() -> {
            while (!QUEUE.isEmpty()) {
                System.out.println(QUEUE.poll());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(20 * 1000);
    }
}

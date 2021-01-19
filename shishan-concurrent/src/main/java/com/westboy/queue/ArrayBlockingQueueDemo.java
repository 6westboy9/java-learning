package com.westboy.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author pengbo
 * @since 2021/1/10
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        queue.put("A");
        String res = queue.take();
        System.out.println(res);
    }
}

package com.westboy.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author pengbo
 * @since 2021/1/10
 */
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("A");
        String res = queue.take();
        System.out.println(res);
    }
}

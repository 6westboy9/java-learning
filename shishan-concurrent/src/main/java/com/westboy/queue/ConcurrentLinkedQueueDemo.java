package com.westboy.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author pengbo
 * @since 2021/1/10
 */
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
        queue.offer("A");
        queue.poll();
    }
}

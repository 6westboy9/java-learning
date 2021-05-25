package com.westboy;

import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(6);
        queue.add(7);
        queue.add(3);
        queue.add(1);

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}

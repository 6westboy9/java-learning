package com.westboy.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author pengbo
 * @since 2021/1/9
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println(Thread.currentThread().getName() + " 凑齐 3 人了，出发！"));
        for (int i = 0; i < 6; i++) {
            new Thread(new Task(i + 1, cyclicBarrier)).start();
        }
    }

    static class Task implements Runnable {
        private final int id;
        private final CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 同学 " + id + " 现在从大门出发，前往自行车驿站");
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(Thread.currentThread().getName() + " 同学 " + id + " 到了自行车驿站，开始等待其他人到达");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " 同学 " + id + " 开始骑车");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

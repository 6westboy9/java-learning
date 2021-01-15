package com.westboy.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author pengbo
 * @since 2021/1/10
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        DelayQueue<DelayEntity> queue = new DelayQueue<>();

    }

    static class DelayEntity implements Delayed {
        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}

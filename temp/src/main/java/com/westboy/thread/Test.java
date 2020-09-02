package com.westboy.thread;

import cn.hutool.core.util.ObjectUtil;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pengbo.wang
 * @date 2020/07/15 2:20 下午
 */
public class Test {

    static final int count = 1000000; // 100万
    static final AtomicInteger atomicInteger = new AtomicInteger();


    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            test(i);
        }
    }

    @SneakyThrows
    private static void test(int j) {
        CountDownLatch downLatch1 = new CountDownLatch(count);
        CountDownLatch downLatch2 = new CountDownLatch(count);

        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Update.print();
        Update update =  new Update("NO000001", downLatch1);
        for (int i = 0; i < count; i++) {
            executor.execute(update);
            executor.execute(new Update("NO000002", downLatch2));
        }

        downLatch1.await();
        downLatch2.await();
        // TimeUnit.SECONDS.sleep(5);
        System.out.println(atomicInteger.get());
        Update.print();
        executor.shutdown();

        System.out.println("第" + j + "遍历共计: " + (System.currentTimeMillis() - start) + "毫秒");
    }



    static class Update implements Runnable {
        private final String key;
        private final CountDownLatch downLatch;

        public Update(String key, CountDownLatch downLatch) {
            this.key = key;
            this.downLatch = downLatch;
        }

        static final Map<String, Integer> MAP = new HashMap<>();
        static final Map<String, ReentrantLock> LOCK_MAP = new HashMap<>();


        public static void print() {
            MAP.forEach((k, v) -> System.out.println("K:" + k + ", V:" + v));
        }


        @Override
        public void run() {
            ReentrantLock lock = LOCK_MAP.get(key);
            if (ObjectUtil.isNull(lock)) {
                synchronized (LOCK_MAP) {
                    lock = LOCK_MAP.get(key);
                    if (ObjectUtil.isNull(lock)) {
                        lock = new ReentrantLock();
                        LOCK_MAP.put(key, lock);
                    }
                }
            }

            // System.out.println(lock);
            lock.lock();
            try {

                // System.out.print("LockMap Size: " + LOCK_MAP.size() + ", ");
                // LOCK_MAP.forEach((k, v) -> System.out.println("K:" + k + ", V:" + v));
                // System.out.println("----------------------------------------------");
                // System.out.println("K: " + key + ", In: " + new Date());
                Integer value = MAP.getOrDefault(key, 0);

                // TimeUnit.SECONDS.sleep(10);

                MAP.put(key, value + 1);
                // System.out.println("K: " + key + ", Out: " + new Date());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                atomicInteger.incrementAndGet();
                lock.unlock();
                downLatch.countDown();
            }
        }
    }


}

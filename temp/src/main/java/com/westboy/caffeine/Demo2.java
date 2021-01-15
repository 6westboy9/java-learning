package com.westboy.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @author pengbo
 * @since 2020/9/3
 */
public class Demo2 {

    public static void main(String[] args) {
        test1();
    }

    @SneakyThrows
    public static void test1() {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,
                3,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("caffeine-cache-task-%d").build());

        Cache<String, String> cache = Caffeine.newBuilder()
                .initialCapacity(2)
                .maximumSize(1)                            // 最大长度
                .executor(executor)
                .expireAfterWrite(2, TimeUnit.SECONDS) // 设置缓存策略在 1 天未写入过期缓存
                .removalListener((String k, String v, RemovalCause cause) -> {
                    System.out.printf(Thread.currentThread() + ": key %s was removed (%s), value is %s%n", k, cause, v);
                })
                .recordStats()
                .build();

        for (int i = 0; i < 3; i++) {
            cache.put("K" + i, "V" + i);
        }

        TimeUnit.SECONDS.sleep(10);

        cache.cleanUp();
        System.out.println(Thread.currentThread() + ": " + cache.estimatedSize());
        System.out.println(Thread.currentThread() + ": " + cache.asMap());
        System.out.println(Thread.currentThread() + ": " + cache.stats());

        executor.shutdown();

    }

}

package com.westboy.caffeine.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;

import java.util.concurrent.TimeUnit;

public class DefaultCache<K, V> implements ICache<K, V> {

    public DefaultCache() {
        System.out.println("初始化默认缓存构造方法...");
    }

    @Override
    public Cache<K, V> create() {
        System.out.println("执行创建默认缓存方法...");
        return Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .removalListener((Object key, Object value, RemovalCause cause) -> {
                    System.out.printf("Key %s was removed (%s)%n", key, cause);
                })
                .recordStats()
                .build();
    }
}

package com.westboy.caffeine.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class StockCache<K, V> implements ICache<K, V> {

    public StockCache() {
        System.out.println("初始化库存缓存构造方法...");
    }

    @Override
    public Cache<K, V> create() {
        System.out.println("执行创建库存缓存方法...");
        return Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build();
    }
}

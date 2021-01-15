package com.westboy.caffeine.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class ProductCache<K, V> implements ICache<K, V> {

    public ProductCache() {
        System.out.println("初始化商品缓存构造方法...");
    }

    @Override
    public Cache<K, V> create() {
        System.out.println("执行创建商品缓存方法...");
        return Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build();
    }
}

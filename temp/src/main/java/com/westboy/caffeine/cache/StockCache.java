package com.westboy.caffeine.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.westboy.caffeine.entity.Product;

import java.util.concurrent.TimeUnit;

public class StockCache<K, V> implements ICache<K, V> {
    @Override
    public Cache<K, V> create() {
        return Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build();
    }
}

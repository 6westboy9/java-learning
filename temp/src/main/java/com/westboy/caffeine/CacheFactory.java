package com.westboy.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.westboy.caffeine.cache.DefaultCache;
import com.westboy.caffeine.cache.ICache;
import com.westboy.caffeine.cache.ProductCache;
import com.westboy.caffeine.cache.StockCache;

public abstract class CacheFactory {

    public static <K, V> Cache<K, V> getCache(CacheEnum cacheEnum) {
        switch (cacheEnum) {
            case PRODUCT:
                ICache<K, V> cache = new ProductCache<>();
                return cache.create();
            case STOCK:
                cache = new StockCache<>();
                return cache.create();
            case DEFAULT:
            default:
                cache = new DefaultCache<>();
                return cache.create();
        }
    }
}

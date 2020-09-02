package com.westboy.caffeine;

import cn.hutool.core.util.ObjectUtil;
import com.github.benmanes.caffeine.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CaffeineCacheManager {

    private static final CaffeineCacheManager instance = new CaffeineCacheManager();

    private CaffeineCacheManager() {
    }

    public static CaffeineCacheManager getInstance() {
        return instance;
    }

    private final Map<String, Cache<Object, Object>> cacheMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <K, V> Cache<K, V> getCache(CacheEnum cacheEnum) {
        Map<String, Cache<Object, Object>> cacheMap = CaffeineCacheManager.getInstance().cacheMap;
        Cache<K, V> cache = (Cache<K, V>) cacheMap.get(cacheEnum.name());
        if (ObjectUtil.isNull(cache)) {
            synchronized (CaffeineCacheManager.getInstance()) {
                cache = (Cache<K, V>) cacheMap.get(cacheEnum.name());
                if (ObjectUtil.isNull(cache)) {
                    cache = CacheFactory.getCache(cacheEnum);
                    cacheMap.put(cacheEnum.name(), (Cache<Object, Object>) cache);
                }
            }
        }
        return cache;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i <10; i++) {
            executorService.execute(() -> {
                Cache<String, String> defaultCache = CaffeineCacheManager.getCache(CacheEnum.DEFAULT);
                System.out.println("默认:" + defaultCache);
                Cache<String, String> productCache = CaffeineCacheManager.getCache(CacheEnum.PRODUCT);
                System.out.println("商品:" + productCache);
                Cache<String, String> stockCache = CaffeineCacheManager.getCache(CacheEnum.STOCK);
                System.out.println("库存:" + stockCache);
            });
        }

        executorService.shutdown();
    }
}

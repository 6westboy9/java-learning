package com.westboy.caffeine;

import cn.hutool.core.util.ObjectUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.westboy.caffeine.cache.ICache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CaffeineCacheManager {

    private static final CaffeineCacheManager instance = new CaffeineCacheManager();

    private CaffeineCacheManager() {
    }

    private static CaffeineCacheManager getInstance() {
        return instance;
    }

    private final Map<String, Cache<Object, Object>> cacheMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    // 当使用 CacheCreatorEnum 后，在初始化执行构造方法的时候，已经实例化 ICache 对象，且不可变
    public static <K, V> Cache<K, V> getCache(CacheKeyEnum cacheEnum) {
        Map<String, Cache<Object, Object>> cacheMap = CaffeineCacheManager.getInstance().cacheMap;
        Cache<K, V> cache = (Cache<K, V>) cacheMap.get(cacheEnum.name());
        if (ObjectUtil.isNull(cache)) {
            synchronized (CaffeineCacheManager.getInstance()) {
                cache = (Cache<K, V>) cacheMap.get(cacheEnum.name());
                if (ObjectUtil.isNull(cache)) {
                    ICache<K, V> iCache = CacheCreatorEnum.match(cacheEnum);
                    if (ObjectUtil.isNull(iCache)) {
                        return null;
                    }
                    // 但是这个创建方法，每调用一次生成一个 Cache 实例对象
                    cache = iCache.create();
                    cacheMap.put(cacheEnum.name(), (Cache<Object, Object>) cache);
                }
            }
        }
        return cache;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                Cache<String, String> defaultCache = CaffeineCacheManager.getCache(CacheKeyEnum.DEFAULT);
                System.out.println("默认:" + defaultCache);
                Cache<String, String> productCache = CaffeineCacheManager.getCache(CacheKeyEnum.PRODUCT);
                System.out.println("商品:" + productCache);
                Cache<String, String> stockCache = CaffeineCacheManager.getCache(CacheKeyEnum.STOCK);
                System.out.println("库存:" + stockCache);
            });
        }
        executorService.shutdown();
    }
}

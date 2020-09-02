package com.westboy.caffeine;

import cn.hutool.core.collection.CollUtil;
import com.github.benmanes.caffeine.cache.*;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author pengbo.wang
 * @date 2020/06/10 6:29 下午
 */
public class Demo1 {
    public static void main(String[] args) {
        String key = "name";
        // manualCache(key);
        // loadingCache(key);
        asyncCache(key);



    }

    private static void manualCache(String key) {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(100)                           // 最大长度
                .expireAfterWrite(1, TimeUnit.DAYS) // 设置缓存策略在 1 天未写入过期缓存
                .build();

        // 根据 key 查询缓存，如果没有返回 null
        String value1 = cache.getIfPresent(key);
        System.out.println("value1:" + value1);

        // 根据 key 查询一个缓存，如果没有调用 createCache 方法，将返回值保存至缓存
        // 如果该方法返回 null，则返回 null
        // 如果该方法抛出异常，则 manualCache.get 抛出异常
        String value2 = cache.get(key, Demo1::createCache);
        System.out.println("value2:" + value2);

        // 新增或更新缓存
        cache.put(key, "Jack");
        String value3 = cache.getIfPresent(key);
        System.out.println("value3:" + value3);

        // 删除缓存
        cache.invalidate(key);
        String value4 = cache.getIfPresent(key);
        System.out.println("value4:" + value4);


    }


    private static void loadingCache(String key) {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(100)                           // 最大长度
                .expireAfterWrite(1, TimeUnit.DAYS) // 设置缓存策略在 1 天未写入过期缓存
            .build(Demo1::createCache);                     // 默认的数据加载实现，当调用 get 方法取值时，如果 key 没有对应的值，就调用这个方法进行加载

        // 根据 key 查询缓存，如果没有返回 null
        String value1 = cache.get(key);
        System.out.println("value1:" + value1);

        // 根据 key 查询一个缓存，如果没有调用 createCache 方法，将返回值保存至缓存
        // 如果该方法返回 null，则返回 null
        // 如果该方法抛出异常，则 manualCache.get 抛出异常
        String value2 = cache.get(key, Demo1::createCache);
        System.out.println("value2:" + value2);

        // 新增或更新缓存
        cache.put(key, "Jack");
        String value3 = cache.get(key);
        System.out.println("value3:" + value3);

        // 删除缓存
        cache.invalidate(key);
        Map<String, String> map = cache.getAll(CollUtil.newArrayList(key));
        System.out.println("map:" + map);
    }

    private static void asyncCache(String key) {
        AsyncCache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(100)                           // 最大长度
                .expireAfterWrite(1, TimeUnit.DAYS) // 设置缓存策略在 1 天未写入过期缓存
                .buildAsync();

        CompletableFuture<String> value = cache.get(key, Demo1::createCache);
        System.out.println("异步请求完成");
        try {
            System.out.println("value:" + value.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        Cache<String, String> syncCache = cache.synchronous();

    }

    private static String createCache(String k) {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("创建时长1秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "default";
    }
}

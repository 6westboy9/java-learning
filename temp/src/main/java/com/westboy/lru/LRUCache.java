package com.westboy.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author pengbo
 * @since 2020/12/29
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    public LRUCache(int size) {
        // 第一个参数为 capacity，Math#ceil 方法为向上取整
        // 使用 (int) Math.ceil(size / 0.75f) 就是避免不必要的扩容
        super((int) Math.ceil(size / 0.75f), 0.75f, true);
        this.CACHE_SIZE = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.CACHE_SIZE;
    }

    public static void main(String[] args) {
        // LRUCache<String, String> cache = new LRUCache<>(3);
        // cache.put("a", "a");
        // cache.put("b", "b");
        // cache.put("c", "c");
        // System.out.println(cache); // {a=a, b=b, c=c}
        // cache.put("d", "d");
        // System.out.println(cache); // {b=b, c=c, d=d}
        System.out.println((int) Math.ceil(3 / 0.75f));
    }
}

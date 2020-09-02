package com.westboy.caffeine.cache;

import com.github.benmanes.caffeine.cache.Cache;

public interface ICache<K, V> {
    Cache<K, V> create();
}

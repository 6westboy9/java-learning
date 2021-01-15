package com.westboy.caffeine;


import com.westboy.caffeine.cache.DefaultCache;
import com.westboy.caffeine.cache.ICache;
import com.westboy.caffeine.cache.ProductCache;
import com.westboy.caffeine.cache.StockCache;

import java.util.Arrays;

public enum CacheCreatorEnum {

    DEFAULT(CacheKeyEnum.DEFAULT, new DefaultCache()),
    PRODUCT(CacheKeyEnum.PRODUCT, new ProductCache()),
    STOCK(CacheKeyEnum.STOCK, new StockCache()),
    ;

    private final CacheKeyEnum name;
    private final ICache cache;

    CacheCreatorEnum(CacheKeyEnum name, ICache cache) {
        System.out.println("初始化...");
        this.name = name;
        this.cache = cache;
    }

    public ICache getCache() {
        return cache;
    }

    public static ICache match(CacheKeyEnum cacheKeyEnum) {
        return Arrays.stream(CacheCreatorEnum.values())
                .filter(cacheCreator -> cacheCreator.name.equals(cacheKeyEnum))
                .findFirst()
                .map(CacheCreatorEnum::getCache)
                .orElse(null);
    }
}

package com.westboy.temp;

import cn.hutool.core.lang.Console;
import cn.hutool.system.SystemUtil;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CaffeineOOMDemo {

    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(5000)
                .removalListener((key, value, cause) ->
                        Console.log(Thread.currentThread().getName() + "，key:" + key + "，value:" + value + "，删除原因:" + cause))
                .expireAfterAccess(20, TimeUnit.SECONDS)
                .build(k -> {
                    Console.log(Thread.currentThread().getName() + "，缓存未命中，请求Redis缓存或者请求数据库数据，然后保存至本地缓存");
                    // 需要查询 Redis 缓存，比如消耗 50ms 请求时间
                    TimeUnit.MILLISECONDS.sleep(50);
                    // 再次缓存该用户的请求
                    return "用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息" + k;
                });

        for (int i = 0; i < 5000; i++) {
            cache.put("用户" + i, "用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息用户信息" + i);
        }



        Console.log("已缓存5000个用户信息，PID=" + SystemUtil.getCurrentPID());

        // 30s 后，大量请求进来，而且是不同用户，注意 i 的取值
        TimeUnit.SECONDS.sleep(20);
        ExecutorService service = Executors.newFixedThreadPool(400);
        for (int i = 10000; i < 2010000; i++) {
            int finalI = i;
            service.submit(() -> {
                Console.log(Thread.currentThread().getName() + "，请求数据：" + cache.get("用户" + finalI));
            });

        }
        TimeUnit.HOURS.sleep(1);
    }
}

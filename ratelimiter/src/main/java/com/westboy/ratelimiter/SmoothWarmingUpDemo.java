package com.westboy.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * 平滑预热限流(SmoothWarmingUp)
 */
public class SmoothWarmingUpDemo {
    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(10, 1000, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 10; i++) {
            //获取一个令牌
            System.out.println(limiter.acquire(100));
        }
    }
}

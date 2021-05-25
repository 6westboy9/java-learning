package com.westboy.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 平滑突发限流(SmoothBursty)
 */
public class SmoothBurstyRateLimitDemo {
    public static void main(String[] args) {
        //QPS = 5，每秒允许5个请求
        RateLimiter limiter = RateLimiter.create(5);
        //limiter.acquire() 返回获取token的耗时，以秒为单位
        for (int i = 0; i < 100; i++) {
            System.out.println(limiter.acquire());
        }
    }
}

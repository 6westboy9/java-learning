package com.westboy.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author pengbo
 * @since 2020/12/24
 */
public class RateLimiterDemo01 {

    public static void main(String[] args) {

        // 1 秒放 5 个令牌，放一个需要 200 毫秒
        // RateLimiter rateLimiter = RateLimiter.create(5.0);
        //
        // IntStream.range(0, 10).forEach(i -> {
        //     // 返回结果为获取令牌时间，单位是秒
        //     System.out.println(rateLimiter.acquire(1));
        // });


        RateLimiter rateLimiter = RateLimiter.create(5.0);

        // 占用后面的资源，需要消耗后续 10 秒
        // System.out.println(rateLimiter.acquire(50));
        IntStream.range(0, 20).forEach(i -> {
            // 返回结果为获取令牌时间，单位是秒
            // System.out.println(rateLimiter.acquire(5));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(rateLimiter.tryAcquire(5, 2, TimeUnit.SECONDS));
        });

    }
}

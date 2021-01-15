package com.westboy.random;

import cn.hutool.core.util.RandomUtil;

/**
 * @author pengbo
 * @since 2020/11/26
 */
public class Test01 {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(RandomUtil.randomInt(0, 3));
        }
    }
}

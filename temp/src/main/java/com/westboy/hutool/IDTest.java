package com.westboy.hutool;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class IDTest {

    public static void main(String[] args) {

        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        for (int i = 0; i < 100; i++) {
            System.out.println(snowflake.nextId());
        }


    }
}

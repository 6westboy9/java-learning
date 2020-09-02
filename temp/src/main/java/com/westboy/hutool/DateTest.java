package com.westboy.hutool;

import cn.hutool.core.date.DateUtil;

/**
 * @author pengbo.wang
 * @date 2020/06/29 4:07 下午
 */
public class DateTest {
    public static void main(String[] args) {
        System.out.println(DateUtil.date(1593294728000L).toJdkDate());
    }
}

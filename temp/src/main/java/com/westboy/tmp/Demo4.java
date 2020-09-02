package com.westboy.tmp;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Date;

/**
 * @author pengbo.wang
 * @date 2020/06/17 3:21 下午
 */
public class Demo4 {
    public static void main(String[] args) {
        // System.out.println(System.getProperty("sun.rmi.transport.tcp.responseTimeout"));
        int number = 100011;
        // %04d  其中4为格式化之后的最小位数
        //如果被格式化数字的位数小于此数值,前面会补0
        String str = String.format("%04d",number);
        // System.out.println(str);

        // System.out.println(String.format("%8s","哈哈哈"));

        // System.out.println(StrUtil.fillAfter("SRD", '0', 8));

        // System.out.println(DateUtil.beginOfDay(new Date()).toJdkDate());
        // System.out.println(DateUtil.endOfDay(new Date()).toJdkDate());
        // for (int i = 0; i < 100; i++) {
        //     System.out.println((int)((Math.random()*9+1)*100000));
        // }

    }
}

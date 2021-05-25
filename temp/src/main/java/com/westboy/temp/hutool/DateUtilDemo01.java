package com.westboy.temp.hutool;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtilDemo01 {
    public static void main(String[] args) throws InterruptedException {
        // String format = "yyyy-M-d HH:mm:ss";
        // System.out.println(DateUtil.format(new Date(), format)); ;

        Date date1 = new Date();
        TimeUnit.SECONDS.sleep(3);
        Date date2 = new Date();


        // System.out.println(date1);
        // System.out.println(date2);
        // System.out.println(DateUtil.isSameDay(date1, date2));

        String dateStr = "2021-04-16+17:00:00";

        Date now = new Date();
        System.out.println(DateUtil.format(now, "yyyy-MM-dd'+'HH:00:00"));
        System.out.println(DateUtil.beginOfDay(now));

    }
}

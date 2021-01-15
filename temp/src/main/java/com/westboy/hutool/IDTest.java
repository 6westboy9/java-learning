package com.westboy.hutool;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.westboy.bean.AlipayFundAuthNotificationContent;

import java.util.Date;
import java.util.HashMap;

public class IDTest {

    public static void main(String[] args) {

        // Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        // for (int i = 0; i < 100; i++) {
        //     System.out.println(snowflake.nextId());
        // }


        // System.out.println(DateUtil.format(new Date(), "yyyyMMddHH") + "-" + RandomUtil.randomNumbers(5));


        // System.out.println(test());


        // Date now = new Date();
        // System.out.println(DateUtil.beginOfDay(now));
        //
        // DateTime dateTime = DateUtil.date();
        // System.out.println(dateTime);
        // // DateTime dateTime = DateUtil.nextMonth();
        // // System.out.println(dateTime);
        // // dateTime.setField(DateField.DAY_OF_MONTH, 6);
        // System.out.println(DateUtil.beginOfDay(dateTime));

        // for (int i = 1; i <= 13; i++) {
        //     System.out.println(getDate(i));
        // }

        // System.out.println(DateUtil.format(new Date(), "yyyy年MM月dd日") + "通过支付宝免密支付还款");

        // System.out.println(DateUtil.parse("2014-09-15 11:23:04", "yyyy-MM-dd HH:mm:ss"));

        // DateTime dateTime1 = DateUtil.date();
        // System.out.println(dateTime1);
        //

        DateTime dateTime2 = DateUtil.date();
        System.out.println(DateUtil.offset(dateTime2, DateField.MINUTE, 30));

        //
        //
        // System.out.println(DateUtil.between(dateTime1, dateTime2, DateUnit.DAY) + 1);
        // System.out.println(DateUtil.between(dateTime2, dateTime1, DateUnit.DAY) + 1);


        // System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        // String str = "{\"gmt_create\":\"2021-01-08 15:42:59\",\"charset\":\"UTF-8\",\"operation_type\":\"FREEZE\",\"sign\":\"TOKzvl+16psvYaZFWEE23L/dg5MxUqQPSBlcpNCZwzHTNFkxyoGrrI120BXFnitSSWTk5ftMr1NFxpXsRo/rPktwQyxCPsVgtoqs5q1pN/t7Fwhr689+ARlXVms7zRpsHV9DHVmehEfQv3HjT4Hxg/ZnWaioVyXOYg69yTDA1duksKMtU+Y6TD50Ilh1WSDmgjqC+LMZS3RP7GYhTxONtD2+hjCrzN+r5oGEfafSuOHjb6mq0fXaJYQIQk53aVUpUHgyI5IUU4Zse84kQ+3Q/i5536y3x/mxP2X0h00rDCHxFNkQtKEm3gon6ZB9JAuFW5pGeG7ukEfNIPE7I2sNwA==\",\"auth_no\":\"2021010810002001550532093090\",\"notify_id\":\"2021010800222154309038501445714736\",\"notify_type\":\"fund_auth_freeze\",\"gmt_trans\":\"2021-01-08 15:43:09\",\"operation_id\":\"20210108927199285505\",\"out_request_no\":\"1347434578431840257\",\"payer_user_id\":\"2088132647605554\",\"app_id\":\"2016062101539662\",\"sign_type\":\"RSA2\",\"amount\":\"0.14\",\"rest_amount\":\"0.14\",\"notify_time\":\"2021-01-08 17:09:13\",\"payee_user_id\":\"2088221946048657\",\"out_order_no\":\"1347434578431840256\",\"payee_logon_id\":\"twp***@tanwuapp.com\",\"version\":\"1.0\",\"total_pay_amount\":\"0.00\",\"total_freeze_amount\":\"0.14\",\"auth_app_id\":\"2016062101539662\",\"total_unfreeze_amount\":\"0.00\",\"status\":\"SUCCESS\",\"payer_logon_id\":\"xue***@gmail.com\"}";
        //
        // AlipayFundAuthNotificationContent content = JSONUtil.toBean(str, AlipayFundAuthNotificationContent.class);
        // System.out.println(JSONUtil.toJsonPrettyStr(content));
    }


    private static Date getDate(int i) {
        DateTime dateTime = DateUtil.date();
        if (i == 1) {
            return DateUtil.beginOfDay(dateTime);
        } else {
            dateTime.setField(DateField.DAY_OF_MONTH, 6);
            return DateUtil.beginOfDay(DateUtil.offsetMonth(dateTime, i - 1));
        }
    }



    private static int test() {
        int i = 1;
        try {
            System.out.println("呵呵呵");
        } finally {
            System.out.println("哈哈哈");
        }

        return i;
    }
}

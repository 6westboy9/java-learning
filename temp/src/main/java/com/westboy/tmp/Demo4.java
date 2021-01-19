package com.westboy.tmp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author pengbo.wang
 * @date 2020/06/17 3:21 下午
 */
public class Demo4 {
    public static void main(String[] args) {
        // System.out.println(System.getProperty("sun.rmi.transport.tcp.responseTimeout"));
        // int number = 100011;
        // %04d  其中4为格式化之后的最小位数
        //如果被格式化数字的位数小于此数值,前面会补0
        // String str = String.format("%04d",number);
        // System.out.println(str);

        // System.out.println(String.format("%8s","哈哈哈"));

        // System.out.println(StrUtil.fillAfter("SRD", '0', 8));

        // System.out.println(DateUtil.beginOfDay(new Date()).toJdkDate());
        // System.out.println(DateUtil.endOfDay(new Date()).toJdkDate());
        // for (int i = 0; i < 100; i++) {
        //     System.out.println((int)((Math.random()*9+1)*100000));
        // }

        // String str = "/template/StockTransferOrderImportTemplate.xlsx";
        // System.out.println(str.substring(str.lastIndexOf("/") + 1));

        // String str = "var returnCitySN = {\"cip\": \"119.137.52.165\", \"cid\": \"440306\", \"cname\": \"广东省深圳市宝安区\"};";
        // String str1 = str.substring(str.indexOf("{"), str.lastIndexOf(";"));
        // System.out.println(str1);
        // JSONObject jsonObject = JSONUtil.parseObj(str1);
        // System.out.println(jsonObject.toString());
        // System.out.println(jsonObject.get("cip", String.class));
        //
        // List<Integer> list = CollUtil.newArrayList(1, 2, 2, 3);
        // String str = CollUtil.join(list, ",");
        // System.out.println(str);
        // List<String> stringList = StrUtil.split(str, ',');
        // System.out.println(stringList);
        //
        // String str1 = "1,2,3, 4, 5";
        // stringList = StrUtil.splitTrim(str1, ",");
        // System.out.println(stringList);

        // BigDecimal g = BigDecimal.valueOf(100.000);
        // System.out.println(g.multiply(BigDecimal.valueOf(1000L)));
        // System.out.println(g.multiply(BigDecimal.valueOf(1000L)).longValue() == 100_000L);
        // Person person = null;
        // Demo4 demo4 = new Demo4();
        // demo4.test(person);
        // System.out.println(person);

        // String order = null;
        // System.out.println("111111111");


        long overdueDays = DateUtil.between(DateUtil.date(1610899200000L), DateUtil.date(new Date()), DateUnit.DAY);
        System.out.println(overdueDays);
    }
    class Person {
        private String name;
    }

    public void test(Person person) {
        person = new Person();
        System.out.println(person);
    }
}

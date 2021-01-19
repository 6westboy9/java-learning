package com.westboy.date;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.westboy.util.BeanCopierUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        // 格式化 2020-05-22 16:29:39
        // System.out.println(DateUtil.format(new Date(), "yyyy-[m]m-[d]d hh:mm:ss"));
        // Date now = new Date();
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d hh:mm:ss");
        // System.out.println(format.format(now));

        // 2020-12-22 04:39:41
        // System.out.println(DateUtil.format(DateUtil.date(1608626381000L), "yyyy-M-d hh:mm:ss"));
        // 2020-5-22 04:36:46
        // System.out.println(DateUtil.format(DateUtil.date(), "yyyy-M-d hh:mm:ss"));

        // System.out.println(DateUtil.format(DateUtil.date(), "yyyy-M-d hh:mm:ss"));
        //
        // //
        // System.out.println(DateUtil.betweenDay(DateUtil.date(), DateUtil.date(1591080533000L), true));
        // System.out.println(DateUtil.betweenDay(DateUtil.date(), DateUtil.date(1591253333000L), true));
        //
        // DateTime dateTime = DateUtil.beginOfDay(DateUtil.date());
        // System.out.println(dateTime);
        //
        // System.out.println(DateUtil.date().isBeforeOrEquals(null));

        // String date = DateUtil.formatLocalDateTime(LocalDateTime.now());
        // System.out.println(date);
        // System.out.println(DateUtil.parseLocalDateTime(date));

        // JSONConfig jsonConfig = new JSONConfig();
        // jsonConfig.setDateFormat("yyyy-MM-dd hh:mm:ss");
        // JSONObject jsonObject=  JSONUtil.createObj(jsonConfig);
        // jsonObject.putAll(BeanUtil.beanToMap(new DateDemo()));
        //
        // System.out.println(jsonObject.toString());
        // System.out.println(JSONUtil.toJsonStr(new DateDemo()));


        // DateTime now1 = DateUtil.date();
        // System.out.println(now1);
        // DateTime now2 = DateUtil.offset(now1, DateField.MINUTE, -10);
        // System.out.println(now2);
        //
        // String s = "第%s期";
        // System.out.println(String.format(s, 2));
        //
        // String s1 = "第1期";
        // int i = s.indexOf("%");
        // int j = s.indexOf("s");
        // System.out.println(i);
        // System.out.println(s.substring(0,i));
        // System.out.println(s.substring(j+ 1))
        // ;


        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Goods goods = randomGoods();
            int count = map.getOrDefault(goods.getId(), 0);
            count = count + 1;
            map.put(goods.getId(), count);
        }

        System.out.println(map);

    }

    private static Goods randomGoods() {
        long basic = 100_000L;
        List<Goods> goodsList = goods();
        Map<Integer, Goods> map = new HashMap<>();

        for (Goods goods : goodsList) {
            map.put(goods.getId(), goods);
        }

        List<WeightRandom.WeightObj<Integer>> weightObjs = goodsList.stream().map(goods -> {
            double weight = goods.getProbability().multiply(BigDecimal.valueOf(basic)).doubleValue();
            return new WeightRandom.WeightObj<>(goods.getId(), weight);
        }).collect(Collectors.toList());
        WeightRandom<Integer> weightRandom = RandomUtil.weightRandom(weightObjs);
        int id = weightRandom.next();
        return map.get(id);
    }



    private static List<Goods> goods() {
        return CollUtil.newArrayList(
                new Goods(1, BigDecimal.valueOf(10)),
                new Goods(2, BigDecimal.valueOf(30)),
                new Goods(3, BigDecimal.valueOf(60))
        );
    }

    @Data
    @AllArgsConstructor
    static class Goods {
        private Integer id;
        private BigDecimal probability;
    }

    @Data
    static class DateDemo {
        private Date date = new Date();
        private LocalDateTime localDateTime = LocalDateTime.now();
    }
}

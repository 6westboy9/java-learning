package com.westboy.temp.hutool;

import cn.hutool.core.util.StrUtil;

import java.nio.charset.StandardCharsets;

public class ZipUtilDemo01 {
    public static void main(String[] args) {
        // ZipUtil.zip("/Users/westboy/WorkSpace/personal/java-learning/temp_log/test.log");


        // String s = new String("�t县镇", Charset.forName("gb2312"));

        String s = "�t县镇";

        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);

        System.out.println(StrUtil.str(bytes, "gb2312"));;


    }
}

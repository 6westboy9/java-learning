package com.westboy.temp;

import cn.hutool.core.util.RuntimeUtil;

public class RuntimeDemo01 {
    public static void main(String[] args) {
        String str = RuntimeUtil.execForStr("ifconfig");
        System.out.println(str);
    }
}

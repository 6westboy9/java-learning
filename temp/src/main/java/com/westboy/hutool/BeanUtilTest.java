package com.westboy.hutool;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;

/**
 * @author pengbo
 * @since 2020/9/9
 */
public class BeanUtilTest {

    public static void main(String[] args) {
        A a = new A();
        a.id = 1;
        a.name = "wang";

        B b = BeanUtil.copyProperties(a, B.class);
        System.out.println(b);


    }

    @Data
    static class A {
        private int id;
        private String name;
    }

    @Data
    static class B {
        private int id;
        private String name;
        private String nickname;
    }
}

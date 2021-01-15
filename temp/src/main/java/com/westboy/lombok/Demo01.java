package com.westboy.lombok;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author pengbo
 * @since 2020/9/21
 */
public class Demo01 {
    public static void main(String[] args) {
        SubAnimal subAnimal = new SubAnimal();
        System.out.println(subAnimal);
        System.out.println(subAnimal.getNum());
    }


    @Data
    static class Animal {
        private int num = 0;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    static class SubAnimal extends Animal {
        private String name;
    }

}


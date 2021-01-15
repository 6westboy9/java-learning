package com.westboy.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author pengbo
 * @since 2020/12/3
 */
public class Test {
    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedHashMap<>();

        map.put(1, "1");
        map.put(3, "2");
        map.put(2, "3");
        map.put(6, "4");
        map.put(4, "5");

        map.forEach((k, v) -> System.out.println(k + ", " + v));
        System.out.println(map.keySet());
    }
}

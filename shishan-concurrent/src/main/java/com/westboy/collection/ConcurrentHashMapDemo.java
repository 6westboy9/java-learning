package com.westboy.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pengbo
 * @since 2021/1/10
 */
public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
        System.out.println(map.get("1"));
    }
}

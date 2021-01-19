package com.westboy;

import java.util.HashMap;

/**
 * @author pengbo
 * @since 2021/1/11
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(3, 3);
        map.put(1, 1);
        map.put(4, 4);
        map.put(2, 2);
        map.put(0, 0);

        System.out.println(map);
    }
}

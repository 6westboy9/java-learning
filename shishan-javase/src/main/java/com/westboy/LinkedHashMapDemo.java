package com.westboy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengbo
 * @since 2021/1/11
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // 默认情况下，只会根据添加顺序
        // LinkedHashMap<String, String> map = new LinkedHashMap<>();

        // 最近最新访问元素在尾部
        LinkedHashMap<String, String> map = new LinkedHashMap<>(10, 0.75f, true);

        map.put("aaa", "aaa");
        map.put("bbb", "bbb");
        map.put("ccc", "ccc");
        map.put("ddd", "ddd");

        print(map);

        map.get("ddd");

        print(map);

        map.put("bbb", "bbb1");

        print(map);

        map.remove("ccc");

        print(map);

    }

    private static void print(LinkedHashMap<String, String> map) {
        // List<String> list = new ArrayList<>();
        // for (Map.Entry<String, String> entry : map.entrySet()) {
        //     list.add(entry.getKey() + ":" + entry.getValue());
        // }
        // System.out.println(list);
        System.out.println(map);
    }
}

package com.westboy;

import java.util.LinkedHashMap;

/**
 * @author pengbo
 * @since 2021/1/11
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // 默认情况下，只会根据添加顺序
        // LinkedHashMap<String, String> map = new LinkedHashMap<>();

        // 如果 accessOrder 为 false，查询和更新不会改变链表中维护的顺序
        // 链表的尾部维护的是最新访问数据，头部则是最长时间没有被访问的数据。

        // 删除某个元素，会将那个元素从链表里给摘除

        // 最近最新访问元素在尾部
        LinkedHashMap<String, String> map = new LinkedHashMap<>(10, 0.75f, true);

        map.put("aaa", "aaa");
        map.put("bbb", "bbb");
        map.put("ccc", "ccc");
        map.put("ddd", "ddd");

        print(map);

        map.get("ccc");

        print(map);

        map.put("bbb", "bbb1");

        print(map);

        // map.remove("ccc");

        // print(map);

        // Map.Entry<String, String> entry = map.entrySet().iterator().next();
        // System.out.println(entry);

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

package com.westboy;

import java.util.HashMap;

/**
 * @author pengbo
 * @since 2021/1/11
 */
public class HashMapDemo {

    /**
     * Retrieve object hash code and applies a supplemental hash function to the
     * result hash, which defends against poor quality hash functions.  This is
     * critical because HashMap uses power-of-two length hash tables, that
     * otherwise encounter collisions for hashCodes that do not differ
     * in lower bits. Note: Null keys always map to hash 0, thus index 0.
     */
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

package com.westboy;

/**
 * @author pengbo.wang
 * @date 2019/8/6
 * @since 1.0
 */
public class Cat {
    private static String name;

    static {
        name = "Tom";
        System.out.println("initializing...");
    }

    @Override
    public String toString() {
        return "Cat{name='" + name + '\'' + '}';
    }
}

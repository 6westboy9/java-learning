package com.westboy.enumeration;

/**
 * @author pengbo
 * @since 2020/9/4
 */
public class Test {
    public static void main(String[] args) {
        Handler handler1 = ColorEnum1.RED.getHandler();
        System.out.println(handler1.hashCode());

        Handler handler2 = ColorEnum1.BLACK.getHandler();
        System.out.println(handler2.hashCode());

        Handler handler3 = ColorEnum2.RED.getHandler();
        System.out.println(handler3.hashCode());

        Handler handler4 = ColorEnum2.BLACK.getHandler();
        System.out.println(handler4.hashCode());

        Handler handler5 = ColorEnum3.RED.getHandler();
        System.out.println(handler5.hashCode());

        Handler handler6 = ColorEnum3.BLACK.getHandler();
        System.out.println(handler6.hashCode());

    }
}

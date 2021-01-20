package com.westboy;

import org.openjdk.jol.info.ClassLayout;

public class MemoryLayoutDemo {

    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}

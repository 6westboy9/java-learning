package com.westboy;

public class MyTest18_1 {

    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");

        loader1.setPath("D:\\jvm\\");
        Class<?> clazz1 = loader1.loadClass("com.westboy.MyTest15");

        System.out.println("class1: " + clazz1.hashCode());
        System.out.println("class1: " + clazz1.getClassLoader());
    }
}

package com.westboy;

public class MyTest17_1 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("D:\\jvm\\");
        Class<?> clazz = loader1.loadClass("com.westboy.MySample");
        System.out.println("class: " + clazz.hashCode());

        // 如果注释掉该行，那么并不会实例化 MySample 对象，即 MySample 构造方法不会被调用
        // 因此不会实例化 MyCat 对象，即没有对 MyCat 进行主动使用，这里就不会加载 MyCat Class
        Object object = clazz.newInstance();
    }
}

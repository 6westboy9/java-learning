package com.westboy.classloader;

import sun.misc.Launcher;


public class MyTest23 {
    public static void main(String[] args) {
        // print("sun.boot.class.path", "启动类加载器");
        // print("java.ext.dirs", "扩展类加载器");
        // print("java.class.path", "应用类加载器");

        // ClassLoader 也是由启动类加载器加载，打印结果为 null
        System.out.println(ClassLoader.class.getClassLoader());
        // 扩展类加载器与系统类加载器也是由启动类加载器所加载的，打印结果为 null
        System.out.println(Launcher.class.getClassLoader());

        // 默认的情况下，java.system.class.loader 属性没有被定义，打印结果为 null，
        // 所以系统类加载器默认指向 Launcher$AppClassLoader
        System.out.println(System.getProperty("java.system.class.loader"));

        System.out.println(MyTest23.class.getClassLoader());
        System.out.println(MyTest16.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        //
        // Class clazz = MyTest23.class;
        // System.out.println(clazz.getClassLoader());
        // System.out.println(Thread.currentThread().getContextClassLoader());
        // System.out.println(ClassLoader.getSystemClassLoader());
    }

    private static void print(String s, String classLoaderName) {
        System.out.println(classLoaderName + "加载路径: ");
        for (String v : System.getProperty(s).split(";")) {
            System.out.println(v);
        }
    }
}

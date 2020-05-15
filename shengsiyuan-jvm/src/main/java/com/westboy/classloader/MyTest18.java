package com.westboy.classloader;

import java.net.URL;

public class MyTest18 {
    public static void main(String[] args) {
        print("sun.boot.class.path", "启动类加载器");
        print("java.ext.dirs", "扩展类加载器");
        print("java.class.path", "应用类加载器");


        // URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        // for (int i = 0; i < urls.length; i++) {
        //     System.out.println(urls[i].toExternalForm());
        // }

    }

    private static void print(String s, String classLoaderName) {
        System.out.println(classLoaderName + "加载路径: ");
        for (String v : System.getProperty(s).split(":")) {
            System.out.println(v);
        }
    }
}

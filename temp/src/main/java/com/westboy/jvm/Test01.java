package com.westboy.jvm;

/**
 * @author pengbo
 * @since 2020/12/21
 */
public class Test01 {
    public static void main(String[] args) {
        print("sun.boot.class.path", "启动类加载器");
        print("java.ext.dirs", "扩展类加载器");
        print("java.class.path", "应用类加载器");
    }

    private static void print(String s, String classLoaderName) {
        System.out.println(classLoaderName + "加载路径: ");
        for (String v : System.getProperty(s).split(":")) {
            System.out.println(v);
        }
    }
}

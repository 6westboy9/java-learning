package com.westboy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyTest16 extends ClassLoader {

    private String classLoaderName;
    private String path;
    private final String fileExtension = ".class";

    public MyTest16(String classLoaderName) {
        // 将系统类加载器当做该类加载器的父加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader classLoader) {
        super(classLoader);
    }


    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MyTest16{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass invoked: " + className);
        System.out.println("class loader name: " + this.classLoaderName);

        byte[] data = this.loadClassData(className);
        return this.defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String className) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        className = className.replace(".", "\\");

        try {
            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            baos = new ByteArrayOutputStream();

            int ch = 0;
            while ((ch = is.read()) != -1) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("C:\\Users\\westboy\\IdeaProjects\\jvm-learning\\shengsiyuan-jvm\\target\\classes\\");
        // loader1.setPath("D:\\jvm\\");
        Class<?> clazz1 = loader1.loadClass("com.westboy.MyTest15");

        System.out.println("class1: " + clazz1.hashCode());
        Object object1 = clazz1.newInstance();
        System.out.println(object1);
        System.out.println(object1.getClass().getClassLoader());

        // System.out.println();
        //
        // MyTest16 loader2 = new MyTest16(loader1, "loader2");
        // // loader2.setPath("C:\\Users\\westboy\\IdeaProjects\\jvm-learning\\shengsiyuan-jvm\\target\\classes\\");
        // loader2.setPath("D:\\jvm\\");
        // Class<?> clazz2 = loader2.loadClass("com.westboy.MyTest15");
        //
        // System.out.println("class2: " + clazz2.hashCode());
        // Object object2 = clazz2.newInstance();
        // System.out.println(object2);
        // System.out.println(object2.getClass().getClassLoader());

        // 命名空间
        // 每个类加载器都有自己的命名空间，命名空间由该加载器及所有父加载器所加载的类组成。
        // 在同一个命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类
        // 在不同的命名空间中，有可能会出现类的完整名字（包括类的包名）相同的两个类

        // System.out.println();
        //
        // loader1 = null;
        // clazz1 = null;
        // object1 = null;
        //
        // System.gc();
        //
        // TimeUnit.MINUTES.sleep(1);
        //
        // System.out.println();
        //
        // loader1 = new MyTest16("loader1");
        // // loader2.setPath("C:\\Users\\westboy\\IdeaProjects\\jvm-learning\\shengsiyuan-jvm\\target\\classes\\");
        // loader1.setPath("D:\\jvm\\");
        // clazz1 = loader1.loadClass("com.westboy.MyTest15");
        //
        // System.out.println("class1: " + clazz1.hashCode());
        // object1 = clazz1.newInstance();
        // System.out.println(object1);
        // System.out.println(object1.getClass().getClassLoader());

    }
}

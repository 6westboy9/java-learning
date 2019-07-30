package com.westboy;

/*
 * 关于命名空间的重要说明
 *
 * 1. 子加载器所加载的类能够访问父加载器所加载的类
 * 2. 父加载器所加载的类无法访问子加载器所加载的类
 */
public class MySample {
    public MySample() {
        System.out.println("MySample is loaded by: " + this.getClass().getClassLoader());
        new MyCat();
        System.out.println("from MySample: " + MyCat.class);
    }
}

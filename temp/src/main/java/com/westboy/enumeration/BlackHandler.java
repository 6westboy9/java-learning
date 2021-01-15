package com.westboy.enumeration;

/**
 * @author pengbo
 * @since 2020/9/4
 */
public class BlackHandler implements Handler{

    public BlackHandler() {
        System.out.println("黑色处理器构造方法");
    }

    @Override
    public Handler getHandler() {
        System.out.println("获取黑色处理器");
        return new BlackHandler();
    }
}

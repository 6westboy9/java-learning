package com.westboy.enumeration;

/**
 * @author pengbo
 * @since 2020/9/4
 */
public class RedHandler implements Handler {

    public RedHandler() {
        System.out.println("红色处理器构造方法");
    }

    @Override
    public Handler getHandler() {
        System.out.println("获取红色处理器");
        return new RedHandler();
    }
}

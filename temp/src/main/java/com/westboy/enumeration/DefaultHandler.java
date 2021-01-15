package com.westboy.enumeration;

/**
 * @author pengbo
 * @since 2020/9/4
 */
public class DefaultHandler implements Handler {

    public DefaultHandler() {
        System.out.println("默认处理器构造方法");
    }

    @Override
    public Handler getHandler() {
        System.out.println("获取默认处理器");
        return new DefaultHandler();
    }
}

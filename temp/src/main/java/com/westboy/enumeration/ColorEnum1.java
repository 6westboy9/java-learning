package com.westboy.enumeration;

/**
 * @author pengbo
 * @since 2020/9/3
 */
public enum ColorEnum1 {

    RED{
        @Override
        public RedHandler getHandler() {
            System.out.println("红色执行其他操作....");
            return new RedHandler();
        }
    },
    BLACK {
        @Override
        public BlackHandler getHandler() {
            System.out.println("黑色执行其他操作....");
            return new BlackHandler();
        }
    };

    public abstract Handler getHandler();

}

package com.westboy.enumeration;

/**
 * @author pengbo
 * @since 2020/9/4
 */
public enum ColorEnum3 implements Handler {

    RED {
        @Override
        public Handler getHandler() {
            return new RedHandler();
        }
    },
    BLACK {
        @Override
        public Handler getHandler() {
            return new BlackHandler();
        }
    },
    ;

    @Override
    public Handler getHandler() {
        return new DefaultHandler();
    }
}

package com.westboy.enumeration;

/**
 * @author pengbo
 * @since 2020/9/4
 */
public enum ColorEnum2 {
    RED(new RedHandler()),
    BLACK(new BlackHandler()),
    ;
    private final Handler handler;

    ColorEnum2(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }
}

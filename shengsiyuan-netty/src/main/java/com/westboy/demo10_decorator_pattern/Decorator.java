package com.westboy.demo10_decorator_pattern;

/**
 * @author pengbo
 * @since 2021/1/27
 */
public class Decorator implements Component {

    private final Component component;

    protected Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}

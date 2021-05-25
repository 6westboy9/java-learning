package com.westboy.demo10_decorator_pattern;

/**
 * @author pengbo
 * @since 2021/1/27
 */
public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }

    private void doAnotherThing() {
        System.out.println("功能B");
    }
}

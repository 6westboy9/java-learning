package com.westboy.demo10_decorator_pattern;

/**
 * @author pengbo
 * @since 2021/1/27
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteDecoratorA(new ConcreteDecoratorB(new ConcreteComponent()));
        component.doSomething();
    }
}

package com.westboy.demo10_decorator_pattern;

/**
 * @author pengbo
 * @since 2021/1/27
 */
public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("具体功能");
    }
}

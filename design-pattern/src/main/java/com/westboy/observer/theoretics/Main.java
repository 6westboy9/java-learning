package com.westboy.observer.theoretics;

/**
 * 主方法
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer obs1 = new ConcreteObserver1();
        Observer obs2 = new ConcreteObserver2();
        subject.attach(obs1);
        subject.attach(obs2);
        subject.notifyObservers();
    }
}

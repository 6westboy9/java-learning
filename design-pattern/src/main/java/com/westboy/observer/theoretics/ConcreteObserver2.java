package com.westboy.observer.theoretics;

/**
 * 具体观察者2，具体观察者（Concrete Observer）角色：实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态。
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public class ConcreteObserver2 implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者2作出响应！");
    }
}

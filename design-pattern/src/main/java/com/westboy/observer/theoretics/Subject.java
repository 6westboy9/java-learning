package com.westboy.observer.theoretics;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题（Subject）角色：也叫抽象目标类，它提供了一个用于保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法。
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public abstract class Subject {

    /**
     * 观察者列表
     */
    protected List<Observer> observers = new ArrayList<>();

    /**
     * 增加观察者方法
     *
     * @param obs 待增加观察者：{@link Observer}
     */
    public void attach(Observer obs) {
        observers.add(obs);
    }

    /**
     * 删除观察者方法
     *
     * @param obs 待删除观察者：{@link Observer}
     */
    public void detach(Observer obs) {
        observers.remove(obs);
    }

    /**
     * 通知观察者方法
     */
    protected abstract void notifyObservers();

}

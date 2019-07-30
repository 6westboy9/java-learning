package com.westboy.observer.practice.example02;

/**
 * 抽象观察者类：铃声事件监听器
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public interface BellEventListener {
    void heardBell(RingEvent e);
}

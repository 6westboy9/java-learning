package com.westboy.observer.practice.example02;

/**
 * 具体观察者类：老师事件监听器
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public class TeachEventListener implements BellEventListener {
    public void heardBell(RingEvent e) {
        if (e.isSound()) {
            System.out.println("老师上课了...");
        } else {
            System.out.println("老师下课了...");
        }
    }
}

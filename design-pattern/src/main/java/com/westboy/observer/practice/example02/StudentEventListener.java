package com.westboy.observer.practice.example02;

/**
 * 具体观察者类：学生事件监听器
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public class StudentEventListener implements BellEventListener {
    @Override
    public void heardBell(RingEvent e) {
        if (e.isSound()) {
            System.out.println("同学们，上课了...");
        } else {
            System.out.println("同学们，下课了...");
        }
    }
}

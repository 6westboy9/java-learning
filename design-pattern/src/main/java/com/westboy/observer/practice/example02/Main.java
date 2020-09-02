package com.westboy.observer.practice.example02;

/**
 * 主方法
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        // 铃（事件源）
        BellEventSource bell = new BellEventSource();
        // 注册监听器（老师）
        bell.addPersonListener(new TeachEventListener());
        // 注册监听器（学生）
        bell.addPersonListener(new StudentEventListener());
        // 打上课铃声
        bell.ring(true);
        System.out.println("----------------");
        // 打下课铃声
        bell.ring(false);
    }
}

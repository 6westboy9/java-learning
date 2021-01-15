package com.westboy.observer.practice.example03;

/**
 * 主方法
 *
 * @author pengbo
 * @since 2020/9/2
 */
public class Main {
    public static void main(String[] args) {
        // 来了一个小丑
        Clown clown = new Clown();
        // 观众入场了
        for (int i = 0; i < 3; i++) {
            Viewer v = new Viewer(i);
            clown.addObserver(v);
            System.out.println("座号为" + v.getSeatNo() + "的观众入座");
        }
        // 小丑开始表演
        clown.perform();
        // 小丑表演完毕
        clown.exit();
    }
}

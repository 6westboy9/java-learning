package com.westboy.observer.practice.example03;

import java.util.Observable;
import java.util.Random;

/**
 * 小丑类
 *
 * @author pengbo
 * @since 2020/9/2
 */
public class Clown extends Observable {
    /**
     * 表演的精彩
     */
    public static final int PERFORM_GOOD = 0;
    /**
     * 表演的糟糕
     */
    public static final int PERFORM_BAD = 1;
    /**
     * 表演完毕
     */
    public static final int PERFORM_COMPLETE = 2;

    /**
     * 表演
     */
    public void perform() {
        System.out.println("**小丑开始表演**");
        int random = new Random().nextInt(2);
        switch (random) {
            case PERFORM_GOOD:
                System.out.println("**小丑状态很好，表演的很精彩！**");
                break;
            case PERFORM_BAD:
                System.out.println("**小丑状态不好，出了点篓子！**");
                break;
        }
        setChanged();
        // 表演好坏通过该参数传递到观众的 update 方法的第二个参数上
        notifyObservers(random);
    }

    /**
     * 表演结束
     */
    public void exit() {
        System.out.println("**表演结束，小丑退场！**");
        setChanged();
        // 退场消息通过该参数传递到观众的 update 方法的第二个参数上
        notifyObservers(PERFORM_COMPLETE);
    }

}

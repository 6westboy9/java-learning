package com.westboy.observer.practice.example02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 目标类：事件源，铃
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public class BellEventSource {

    private final List<BellEventListener> listener = new ArrayList<>();

    public void addPersonListener(BellEventListener ren) {
        listener.add(ren);
    }

    public void ring(boolean sound) {
        System.out.println((sound ? "上课铃" : "下课铃") + "响！");
        // 通知注册在该事件源上的所有监听器
        notifies(new RingEvent(sound));
    }

    private void notifies(RingEvent e) {
        listener.forEach(bellEventListener -> bellEventListener.heardBell(e));
    }
}

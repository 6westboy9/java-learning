package com.westboy;

import java.util.concurrent.TimeUnit;

public class MyTest25 implements Runnable{

    private Thread thread;

    public MyTest25() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ClassLoader classLoader = this.thread.getContextClassLoader();
        this.thread.setContextClassLoader(classLoader);
        System.out.println(classLoader);
        System.out.println(classLoader.getParent().getClass());
    }

    public static void main(String[] args) throws InterruptedException {
        new MyTest25();
        // TimeUnit.MINUTES.sleep(1);
    }
}

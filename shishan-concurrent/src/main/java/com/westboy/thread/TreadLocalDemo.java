package com.westboy.thread;

public class TreadLocalDemo {

    private static final ThreadLocal<String> threadLocal1 = ThreadLocal.withInitial(() -> "初始化值");

    private static final ThreadLocal<Long> threadLocal2 = ThreadLocal.withInitial(() -> 0L);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new MyRunnable(), "线程-" + i).start();
        }
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            threadLocal1.set(name);
            threadLocal2.set(System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                threadLocal1.remove();
            }
            System.out.println(name + "：" + threadLocal1.get() + "，耗时：" + (System.currentTimeMillis() - threadLocal2.get()) + "ms");
        }
    }
}

package com.westboy.temp;

public class RuntimeDemo02 {

    private static final Object lock = new Object();
    private static volatile boolean terminated = false;

    public static void main(String[] args) throws InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("hook");
            shutdown();
        }));
        System.out.println("main done.");
        await();
    }

    public static void shutdown() {
        synchronized (RuntimeDemo02.lock) {
            terminated = true;
            lock.notify();
        }
    }

    public static void await() throws InterruptedException {
        synchronized(lock) {
            while(!terminated) {
                lock.wait();
            }
        }
    }

}

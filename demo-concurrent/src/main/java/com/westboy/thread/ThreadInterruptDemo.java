package com.westboy.thread;

/**
 * @author pengbo
 * @since 2021/1/11
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Worker());
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }


    static class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " 循环执行...");
                // try {
                //     Thread.sleep(1000);
                // } catch (InterruptedException e) {
                //     System.out.println(Thread.currentThread().getName() + " 休眠中被中断...");
                //     // 休眠中的线程被中断后，中断标识被重置，
                //     e.printStackTrace();
                //     // 重新设置中断标识
                //     Thread.currentThread().interrupt();
                // }
            }
        }
    }
}

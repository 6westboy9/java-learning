package com.westboy.thread;

/**
 * @author pengbo
 * @since 2021/1/11
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) {
        // Thread thread = new Thread(new Worker());
        Worker thread1 = new Worker();
        Worker thread2 = new Worker();
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread1);
        System.out.println(thread2);

        // 设置中断状态
        thread1.interrupt();
        thread2.interrupt();
    }


    static class Worker extends Thread {
        @Override
        public void run() {
            while (true) {

                // 只是判断当前线程是否被中断，会清除中断标识
                // interrupted 为静态方法，只会作用于当前线程，底层实现逻辑：currentThread().isInterrupted(true) 最终都调用了底层的 isInterrupted 方法，参数 true 表示清除中断标识
                if (Thread.interrupted()) {
                    break;
                }

                // 只是判断当前线程是否被中断，不清除中断标识
                // isInterrupted 方法可以在 A 线程中检查 B 线程是否中断，底层实现逻辑：isInterrupted(false) 最终都调用了底层的 isInterrupted 方法，参数 false 表示不清除中断标识
                // if (Thread.currentThread().isInterrupted()) {
                //     break;
                // }

                System.out.println(Thread.currentThread().getName() + " 循环执行...");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    // ========================== 正确的处理中断线程方法 ==========================
                    System.out.println(Thread.currentThread().getName() + " 休眠中被中断...");
                    e.printStackTrace();
                    // 因为线程在休眠期间被中断，那么会自动清除中断信号
                    // 所以，可以再次调用 interrupt 方法重新设置中断标识（在此中断）
                    // Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread() == this);
                    interrupt();
                    // System.out.println(this);
                }
            }
        }
    }
}

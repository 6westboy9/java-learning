package com.westboy.thread;

public class ThreadJoinDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Work());
        thread.start();
        try {
            // 底层调用的 wait 方法
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("阻塞等待等待 join 执行完成");
    }


    static class Work implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("工作线程执行");
        }
    }
}

package com.westboy.thread;

public class ThreadLocalTest {
	private final static ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
	private final static ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

	public static void main(String[] args) {

		new Thread(() -> {
			try {
				for (int i = 0; i < 100; i++) {
					threadLocal1.set(i);
					threadLocal2.set(i * 2);
					System.out.println(Thread.currentThread().getName() + ":" + threadLocal1.get() + ":" + threadLocal2.get());
					sleep(200);
				}
			} finally {
				threadLocal1.remove();
				threadLocal2.remove();
			}
		}, "Thread-1").start();

		new Thread(() -> {
			try {
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + threadLocal1.get() + ":" + threadLocal2.get());
					sleep(200);
				}
			} finally {
				threadLocal1.remove();
			}
		}, "Thread-2").start();
	}

	private static void sleep(long mills) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

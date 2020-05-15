package com.westboy.thread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.LockSupport;

/**
 * @author westboy
 * @since 2020/4/10
 */
public class LockSupportTest {
	@SneakyThrows
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			LockSupport.park("123");
			System.out.println(Thread.currentThread().getName() + " 被唤醒");
		});
		thread.start();

		Thread.sleep(3000);
		LockSupport.unpark(thread);

		System.out.println(Thread.interrupted());
	}
}

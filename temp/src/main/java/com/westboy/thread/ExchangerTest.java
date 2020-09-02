package com.westboy.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author westboy
 * @since 2020/4/10
 */
public class ExchangerTest {


	private static final Exchanger<String> exchanger = new Exchanger<>();
	private static final ExecutorService executor = Executors.newFixedThreadPool(3);

	public static void main(String[] args) {
		executor.execute(() -> {
			String a = "银行流水A";
			try {
				exchanger.exchange(a);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		executor.execute(() -> {
			String b = "银行流水B";
			String a = "";
			try {
				a = exchanger.exchange(b);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A和B数据是否一致：" + a.equals(b) + "，A录入的是：" + a + "，B录入的是：" + b);
		});



		executor.shutdown();
	}
}

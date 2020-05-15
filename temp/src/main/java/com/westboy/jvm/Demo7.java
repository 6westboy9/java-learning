package com.westboy.jvm;

/**
 * @author westboy
 * @since 2019/11/24
 */
public class Demo7 {

	public static void main(String[] args) throws InterruptedException {

		Thread.sleep(30000);

		while (true) {
			loadData();
		}
	}


	private static void loadData() throws InterruptedException {
		byte[] data = null;
		for (int i = 0; i < 50; i++) {
			data = new byte[100 * 1024];
		}
		data = null;
		Thread.sleep(1000);
	}
}

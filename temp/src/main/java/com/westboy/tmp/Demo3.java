package com.westboy.tmp;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.Buffer;

import static java.math.RoundingMode.FLOOR;

/**
 * @author westboy
 * @since 2019/12/27
 */
public class Demo3 {

	@SneakyThrows
	public static void main(String[] args) {
		// for (int i = 0; i < 10; i++) {
		// 	if (i != 3) {
		// 		System.out.println(i);
		// 	}
		// }

		// BigDecimal decimal = BigDecimal.valueOf(12.7d);
		// decimal = decimal.setScale(0, FLOOR);
		// System.out.println(decimal);

		Demo3 demo = new Demo3();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 10; i++) {
			reader.readLine();
			int a = (int) Math.round(Math.random() * 1000);
			int b = (int) Math.round(Math.random() * 1000);
			System.out.println(demo.add(a, b));
		}
	}

	public int add(int a, int b) {
		return a + b;
	}

}

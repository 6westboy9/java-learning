package com.westboy.io.buffer;

import java.nio.charset.Charset;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class CharsetTest {
	public static void main(String[] args) {
		System.out.println(System.getProperty("file.encoding"));
		System.out.println();
		Charset.availableCharsets().forEach((k, v) -> System.out.println(k + "--->" + v));
	}
}

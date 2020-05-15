package com.westboy.jvm;

/**
 * @author westboy
 * @since 2019/11/19
 */
public class Demo5 {
	public static void main(String[] args) {
		byte[] array1 = new byte[7 * 1024 * 1024];
		array1 = null;
		byte[] array2 = new byte[128 * 1024];
		byte[] array3 = new byte[7 * 1024 * 1024]; // 第一次触发 Young GC
		array3 = null;
		byte[] array4 = new byte[2 * 1024 * 1024]; // 第二次触发 Young GC
	}
}

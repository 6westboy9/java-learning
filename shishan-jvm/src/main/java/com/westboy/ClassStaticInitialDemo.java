package com.westboy;

/**
 * @author westboy
 * @since 2019/11/6
 */
public class ClassStaticInitialDemo {

	static {
		a = 3;
	}

	private static int a = 0;

	public static void main(String[] args) {
		System.out.println(ClassStaticInitialDemo.a);
	}
}

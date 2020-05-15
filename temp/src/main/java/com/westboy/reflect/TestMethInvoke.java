package com.westboy.reflect;

import java.lang.reflect.Method;

public class TestMethInvoke {

	public static void main(String[] args) throws Exception {
		Class<?> clz = Class.forName("A");
		Object object = clz.newInstance();
		Method m = clz.getMethod("foo", String.class);
		for (int i = 0; i < 100; i++) {
			m.invoke(object, Integer.toString(i));
		}

	}

	static class A {
		public void foo(String name) {
			System.out.println("Hello, " + name);
		}
	}
}

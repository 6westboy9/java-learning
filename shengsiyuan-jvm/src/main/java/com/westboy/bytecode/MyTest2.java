package com.westboy.bytecode;

/**
 * @author westboy
 * @since 2019/12/3
 */
public class MyTest2 {
	String str = "welcome";
	private int x = 5;
	public static Integer in = 10;

	public MyTest2() {
	}

	public MyTest2(int x) {
		this.x = x;
	}

	public static void main(String[] args) {
		MyTest2 myTest2 = new MyTest2();
		myTest2.setX(8);
		in = 20;
	}

	private synchronized void setX(int x) {
		this.x = x;
	}
}

package com.westboy.uml;

/**
 * @author westboy
 * @since 2019/11/27
 */
public class Car implements Vehicle {

	private Tyre tyre;
	private Engine engine;

	@Override
	public void run() {
		System.out.println("car running...");
	}
}

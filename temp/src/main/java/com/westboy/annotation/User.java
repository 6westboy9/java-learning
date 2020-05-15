package com.westboy.annotation;

/**
 * @author westboy
 * @since 2020/1/2
 */
public class User {
	@MyAnnotation(name = "zhang")
	private String name;
	@MyAnnotation(email = "zhang@example.com")
	private String email;
	@MyAnnotation(name = "sayHelloWorld")
	public String sayHello(){
		return "";
	}
}

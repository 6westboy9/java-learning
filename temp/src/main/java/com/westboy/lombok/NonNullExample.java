package com.westboy.lombok;

import lombok.NonNull;

/**
 * @author westboy
 * @since 2020/4/8
 */

public class NonNullExample {
	private String name;
	private Integer id;

	// 局部变量使用
	public NonNullExample(@NonNull String name, @NonNull Integer userId) {
		id = userId;
		this.name = name;
	}

	@NonNull
	public void setName(String name) {
		this.name = name;
	}

	@NonNull
	public String getName() {
		return name;
	}
}

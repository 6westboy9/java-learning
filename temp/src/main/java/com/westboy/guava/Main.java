package com.westboy.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class Main {

	public static void main(String[] args) {
		Cache<String, String> cache = CacheBuilder.newBuilder().build();
		cache.put("hello", "world");
		System.out.println(cache.getIfPresent("hello"));
	}
}

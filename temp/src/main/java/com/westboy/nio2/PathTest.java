package com.westboy.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class PathTest {
	public static void main(String[] args) {
		Path path = Paths.get(".");
		System.out.println(path.getFileName());
		System.out.println(path.toAbsolutePath().getFileName());
		System.out.println(path.getNameCount());
		System.out.println(path.getRoot());
		System.out.println(path.toAbsolutePath().getRoot());
		System.out.println(path.toAbsolutePath().getNameCount());


	}
}

package com.westboy.nio2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class FileVisitorTest {
	public static void main(String[] args) throws IOException {


		Files.walkFileTree(Paths.get("/Users/westboy/IdeaProjects/personal/java-learning/temp/src/main/java/com/westboy/nio2"), new SimpleFileVisitor<Path>(){
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
				System.out.println("正在访问 " + file + " 文件");
				return FileVisitResult.CONTINUE;
			}
		});

	}
}

package chapter_15_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author pengbo
 * @since 2021/2/24
 */
public class AttributeViewTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/westboy/IdeaProjects/personal/java-learning/crazy-java/src/main/java/chapter_15_10/AttributeViewTest.java");
        BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes basicAbs = basicView.readAttributes();
        System.out.println("创建时间：" + basicAbs.creationTime());
        System.out.println("最近访问时间：" + basicAbs.lastAccessTime());
        System.out.println("最近更新时间：" + basicAbs.lastModifiedTime());
        System.out.println("文件大小：" + basicAbs.size());
    }
}

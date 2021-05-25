package com.westboy;

import cn.hutool.core.io.FileUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

public class FileDemo01 {
    public static void main(String[] args) throws IOException {
        // 递归遍历指定目录下的文件
        // loopFiles();

        // throw new FileNotFoundException("111111");
        //
        // String str = "111111111";
        // System.out.println(str.getBytes().length);
        // System.out.println(str.length());

        String localPath = "/Users/westboy/WorkSpace/personal/java-learning/temp/src/main/java/com/westboy/FileDemo01.java";
        FileInputStream fileInputStream = new FileInputStream(localPath);
        FileChannel channel = fileInputStream.getChannel();



        System.out.println(channel.size());

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read;

        while ((read = channel.read(buffer)) > 0) {

        }



        System.out.println(read);

        System.out.println(buffer);

    }

    private static void loopFiles() {
        List<File> files = FileUtil.loopFiles("/Users/westboy/WorkSpace/personal/java-learning/shishan-jvm/src/main/java/com/westboy");
        files.forEach(file -> {
            try {
                System.out.println(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}

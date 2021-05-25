package com.westboy.temp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

        String localPath = "/Users/westboy/WorkSpace/personal/java-learning/temp/src/main/resources/temp/FileDemo01.java";
        FileInputStream fileInputStream = new FileInputStream(localPath);
        FileChannel channel = fileInputStream.getChannel();

        String copy = "/Users/westboy/WorkSpace/personal/java-learning/temp/src/main/resources/temp/FileDemo01.java.cp";

        FileOutputStream fileOutputStream = new FileOutputStream(copy);
        FileChannel outChannel = fileOutputStream.getChannel();

        System.out.println(channel.size());

        // ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int read;

        int readCount = 0;
        while ((read = channel.read(buffer)) > 0) {

            // System.out.println(read);

            byte[] array = buffer.array();
            System.out.print(new String(array, 0, read, StandardCharsets.UTF_8));


            buffer.flip(); // buffer 转为读取，区别于 rewind 底层会将 limit = position; 表示下次读的时候最多读到 limit
            outChannel.write(buffer);
            readCount += read;
            buffer.rewind(); // buffer 转为写入
        }

        System.out.println(readCount);

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

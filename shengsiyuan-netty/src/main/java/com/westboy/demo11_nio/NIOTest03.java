package com.westboy.demo11_nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author pengbo
 * @since 2021/1/27
 */
public class NIOTest03 {
    public static void main(String[] args) throws IOException {
        String filePath = "shengsiyuan-netty-tmp/NIOTest03.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] message = "hello world".getBytes();

        // 先写入 byteBuffer
        for (byte b : message) {
            byteBuffer.put(b);
        }

        byteBuffer.flip();

        // 读取 byteBuffer 写入 fileChannel 对应的磁盘文件中
        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}

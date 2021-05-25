package com.westboy.demo11_nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author pengbo
 * @since 2021/1/27
 */
public class NIOTest02 {
    public static void main(String[] args) throws IOException {
        String filePath = "shengsiyuan-netty-tmp/NIOTest02.txt";
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        // 将磁盘文件中的数据读取后写入到 byteBuffer 中
        fileChannel.read(byteBuffer);

        // 切换为读取 byteBuffer
        byteBuffer.flip();
        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println("Character: " + (char) b);
        }
        fileInputStream.close();
    }
}

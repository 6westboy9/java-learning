package com.westboy.demo11_nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author pengbo
 * @since 2021/2/6
 */
public class NIOTest08 {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "shengsiyuan-netty-tmp/NIOTest08-input.txt";
        String outputFilePath = "shengsiyuan-netty-tmp/NIOTest08-output.txt";

        FileInputStream inputStream = new FileInputStream(new File(inputFilePath));
        FileOutputStream outputStream = new FileOutputStream(new File(outputFilePath));

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        // 本示例重点：allocateDirect(1024) 与 allocate(1024) 区别
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            buffer.clear();                       // 如果不使用 buffer.clear()，就会导致一直重复写入第一次写入到 buffer 的数据
            // System.out.println(buffer);
            int read = inputChannel.read(buffer); // 如果不使用 buffer.clear()，read 值为 0
            // System.out.println(read);
            if (read == -1) {
                break;
            }
            buffer.flip();
            outputChannel.write(buffer);
        }
    }
}

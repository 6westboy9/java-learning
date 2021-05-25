package com.westboy.demo11_nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author pengbo
 * @since 2021/2/6
 */
public class NIOTest04 {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "shengsiyuan-netty-tmp/NIOTest04_In.txt";
        String outputFilePath = "shengsiyuan-netty-tmp/NIOTest04_Out.txt";

        FileInputStream inputStream = new FileInputStream(new File(inputFilePath));
        FileOutputStream outputStream = new FileOutputStream(new File(outputFilePath));

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            // 在读取模式下，调用 clear() 方法将缓冲区切换为写入模式。此方法会将 position 清零， limit 设置为 capacity 最大容量值，可以一直写入，直到缓冲区写满
            buffer.clear();                       // 如果不使用 buffer.clear()，就会导致一直重复写入第一次写入到 buffer 的数据
            // System.out.println(buffer);

            // ① 读取 inputChannel 对应的磁盘文件上的数据，并写入到 buffer 中
            int read = inputChannel.read(buffer); // 如果不使用 buffer.clear()，read 值为 0
            // System.out.println(read);
            if (read == -1) {
                break;
            }
            buffer.flip();

            // ② 将 buffer 中的数据写入到 outputChannel 对应的磁盘文件上
            outputChannel.write(buffer);
        }
    }
}

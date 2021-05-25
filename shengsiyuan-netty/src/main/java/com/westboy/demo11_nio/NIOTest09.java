package com.westboy.demo11_nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * mmap 技术
 * @author pengbo
 * @since 2021/2/6
 */
public class NIOTest09 {
    public static void main(String[] args) throws IOException {
        String filePath = "shengsiyuan-netty-tmp/NIOTest09.txt";
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");

        FileChannel channel = file.getChannel();

        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        // hello
        // aelbo
        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(3, (byte) 'b');


        // 操作系统异步刷盘

    }
}

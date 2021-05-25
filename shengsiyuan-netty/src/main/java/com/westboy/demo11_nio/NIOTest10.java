package com.westboy.demo11_nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * 文件锁
 * @author pengbo
 * @since 2021/2/6
 */
public class NIOTest10 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "shengsiyuan-netty-tmp/NIOTest10.txt";
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");

        FileChannel channel = file.getChannel();

        // FileLock lock = channel.lock(0, file.length(), true);

        FileLock lock = channel.lock();
        System.out.println("start time: " + LocalDateTime.now());

        // 因为获取不到锁就会抛异常：java.nio.channels.OverlappingFileLockException
        // new Thread(new UpdateThread(channel)).start();

        Thread.sleep(10 * 1000L);

        // ByteBuffer buffer = ByteBuffer.wrap("world".getBytes());
        // channel.write(buffer);
        // channel.force(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();

        Charset charset = StandardCharsets.UTF_8;
        System.out.println("content: " + charset.decode(buffer));


        System.out.println("valid: " + lock.isValid());
        System.out.println("lock type: " + lock.isShared());

        lock.release();

        System.out.println("end time: " + LocalDateTime.now());

        // 在这里就没有问题
        // new Thread(new UpdateThread(channel)).start();

        Thread.sleep(10 * 1000L);

        channel.close();
        file.close();
    }

    static class UpdateThread implements Runnable {

        private final FileChannel channel;

        public UpdateThread(FileChannel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            try {
                FileLock lock = channel.lock();
                ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
                System.out.println(channel.position());
                channel.write(buffer);
                channel.force(false);
                lock.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

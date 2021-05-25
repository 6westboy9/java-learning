package com.westboy.demo12_nio_zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author pengbo
 * @since 2021/2/25
 */
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        // serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(8899));

        ByteBuffer buffer = ByteBuffer.allocate(4096);

        while (true) {
            // 返回的 SocketChannel 为阻塞，与 ServerSocketChannel 阻塞与否无关
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true); // 不用指定都是阻塞的

            int readCount = 0;
            int total = 0;
            while (readCount != -1) {
                try {
                    readCount = socketChannel.read(buffer); // 读到直接丢弃
                    total = total + readCount;
                    buffer.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("读取总数：" + total);
            }
        }

    }
}

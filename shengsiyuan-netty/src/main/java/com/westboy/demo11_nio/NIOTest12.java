package com.westboy.demo11_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author pengbo
 * @since 2021/2/6
 */
public class NIOTest12 {

    public static void main(String[] args) throws IOException {
        int[] ports = {5000, 5001, 5002, 5003, 5004};

        Selector selector = Selector.open(); // 默认实现类：KQueueSelectorImpl
        System.out.println(selector);

        for (int port : ports) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            // A selection key is created each time a channel is registered with a selector. -- 重要
            // 每次 channel 注册至 selector 时，就会创建一个 selection key
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口：" + port);
        }

        // 客户端连接，可以使用 nc 工具
        // nc localhost 5000
        // nc localhost 5001
        // nc localhost 5002
        // nc localhost 5003
        // nc localhost 5004
        while (true) {
            int nums = selector.select();
            System.out.println("nums：" + nums);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys：" + selectionKeys);

            try {
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // iterator.remove();

                    if (!selectionKey.isValid()) {
                        System.out.println("无效 selectionKey：" + selectionKey);
                        continue;
                    }

                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = channel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("获取客户端连接：" + socketChannel);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        try {
                            int bytesRead = 0;
                            while (true) {
                                ByteBuffer buffer = ByteBuffer.allocate(512);
                                buffer.clear();

                                int read = socketChannel.read(buffer);
                                if (read <= 0) {
                                    break;
                                }

                                buffer.flip();
                                socketChannel.write(buffer);
                                bytesRead += read;
                            }
                            System.out.println("读取：" + bytesRead + "，来自于：" + socketChannel);
                        } catch (Exception e) {
                            // 客户端断开连接时，关闭 socketChannel
                            socketChannel.close();
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                selectionKeys.clear();
                System.out.println("清空 selectionKeys：" + selectionKeys.size());
            }

        }
    }

}

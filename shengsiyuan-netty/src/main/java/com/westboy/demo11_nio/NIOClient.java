package com.westboy.demo11_nio;

import cn.hutool.core.date.DateUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengbo
 * @since 2021/2/23
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKey1 = socketChannel.register(selector, SelectionKey.OP_CONNECT);
        // 发起连接
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));


        System.out.println("socketChannel=" + socketChannel.hashCode()); // ①

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            System.out.println("处理当前一批监听到的事件：" + selectionKeys.size());

            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isConnectable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();  // 根 ① 是同一个对象

                    System.out.println("socketChannel=" + client.hashCode());

                    if (client.isConnectionPending()) {
                        client.finishConnect();

                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.put((LocalDateTime.now() + " 连接成功").getBytes());
                        buffer.flip();

                        client.write(buffer);

                        System.out.println("发送连接连接建立成功");

                        // 起一个新的线程，从终端发送消息至服务端
                        ExecutorService executorService = Executors.newSingleThreadExecutor();
                        executorService.submit(() -> {
                            while (true) {
                                try {
                                    buffer.flip();
                                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                                    String message = in.readLine();
                                    buffer.put(message.getBytes());
                                    buffer.flip();
                                    client.write(buffer);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        client.register(selector, SelectionKey.OP_READ);
                    }
                } else if (selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel(); // 后续交互都是 SocketChannel 类型
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = client.read(buffer);
                    if (count > 0) {
                        buffer.flip();
                        Charset charset = StandardCharsets.UTF_8;
                        String receiveMsg = String.valueOf(charset.decode(buffer).array());
                        System.out.println("接收到服务端:[" + client.getRemoteAddress().toString().substring(1) + "]消息: " + receiveMsg);
                    }
                }
            }
            selectionKeys.clear();
        }
    }
}

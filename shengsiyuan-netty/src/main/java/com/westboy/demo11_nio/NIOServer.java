package com.westboy.demo11_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pengbo
 * @since 2021/2/22
 */
public class NIOServer {

    private static final AtomicInteger clientId = new AtomicInteger(1);
    private static final Map<String, SocketChannel> clientMap = new HashMap<>();
    private static final Map<SocketChannel, String> clientNameMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8899), 1);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // ①

        while (true) {
            // 该方法执行是一个阻塞操作
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            System.out.println("处理当前一批监听到的事件：" + selectionKeys.size());

            selectionKeys.forEach(selectionKey -> {
                try {
                    if (selectionKey.isAcceptable()) {
                        System.out.println("连接事件处理...");
                        // 为什么是 ServerSocketChannel 呢？因为一开始时，在 ① 处我们仅仅是将 serverSocketChannel 注册到 channel 上
                        // 在 selector 上获取到 OP_ACCEPT 事件时的 channel 对象都是 ServerSocketChannel 类型，表示有客户端在进行服务连接请求
                        ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();

                        // 获取一个关联客户端的 SocketChannel 对象，并将其也注册到 selector 上
                        SocketChannel client = socketChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ); // 监听来自客户端的读写请求

                        String clientName = "client-" + clientId.getAndIncrement();
                        clientMap.put(clientName, client); // 缓存与客户端交互的 socketChannel 数据
                        clientNameMap.put(client, clientName);

                        System.out.println("接收客户端请求:[" + clientName + ":" + client.getRemoteAddress().toString().substring(1) + "]发起连接请求...");

                        String responseMsg = "Hello, " + clientName;
                        ByteBuffer buffer = ByteBuffer.wrap((responseMsg).getBytes());
                        client.write(buffer);
                        System.out.println("响应客户端请求:[" + clientName + ":" + client.getRemoteAddress().toString().substring(1) + "]" + responseMsg);

                    } else if (selectionKey.isReadable()) {
                        System.out.println("读取事件处理...");

                        SocketChannel client = (SocketChannel) selectionKey.channel(); // 后续交互都是 SocketChannel 类型

                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        // 读取客户端数据，并写入到 buffer 中
                        int count = client.read(buffer);
                        if (count > 0) {
                            buffer.flip();
                            Charset charset = StandardCharsets.UTF_8;
                            String receiveMsg = String.valueOf(charset.decode(buffer).array());

                            String clientName = clientNameMap.get(client);

                            System.out.println("接收客户端请求:[" + clientName + ":" + client.getRemoteAddress().toString().substring(1) + "]发送消息: " + receiveMsg); // 使用 nc 工具时 receiveMsg 最后一个字符为换行符

                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                SocketChannel sc = entry.getValue();
                                if (!sc.equals(client)) {
                                    // 如果客户端正常通信中关闭了，会导致写入该客户端数据异常，具体异常信息：java.io.IOException: Connection reset by peer
                                    sc.write(ByteBuffer.wrap(receiveMsg.getBytes()));
                                }
                            }
                        }
                    } else if (selectionKey.isWritable()) {
                        System.out.println("写入事件处理...");

                        SocketChannel client = (SocketChannel) selectionKey.channel(); // 后续交互都是 SocketChannel 类型
                        String clientName = clientNameMap.get(client);
                        String responseMsg = "Hello, " + clientName;
                        ByteBuffer buffer = ByteBuffer.wrap((responseMsg).getBytes());
                        client.write(buffer);
                        System.out.println("响应客户端请求:[" + clientName + ":" + client.getRemoteAddress().toString().substring(1) + "]" + responseMsg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            selectionKeys.clear(); // 切记处理完成之后一定要清空
        }
    }
}

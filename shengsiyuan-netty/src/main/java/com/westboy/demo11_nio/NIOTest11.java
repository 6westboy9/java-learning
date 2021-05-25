package com.westboy.demo11_nio;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author pengbo
 * @since 2021/2/6
 */
public class NIOTest11 {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);

        // 阻塞
        System.out.println(serverChannel.isBlocking());

        serverChannel.socket().bind(address);


        int messageLength = 2 + 3 + 4;


        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        // accept() 是阻塞方法，只有当有客户端连接后才会返回
        SocketChannel channel = serverChannel.accept();

        // 阻塞
        System.out.println(channel.isBlocking());

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                // 客户端使用 nc localhost 8899 工具进行输入
                // TODO 当客户端断开连接后，发现 read 并不会阻塞，直接返回 -1，每次 byteRead 都 -1，一直循环打印
                // 读取客户端数据，并写入到 buffers 中
                long read = channel.read(buffers);
                byteRead += read;

                Thread.sleep(3 * 1000L);
                System.out.println("byteRead: " + byteRead);

                Arrays.asList(buffers).forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(Buffer::flip);

            // 读慢 9 个字节之后就会会写，否则客户端收不到响应
            long byteWritten = 0;
            while (byteWritten < messageLength) {
                long read = channel.write(buffers);
                byteWritten += read;
            }

            Arrays.asList(buffers).forEach(Buffer::clear);
        }
    }
}

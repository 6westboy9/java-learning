package com.westboy.demo12_nio_zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author pengbo
 * @since 2021/2/25
 */
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));
        socketChannel.configureBlocking(true);

        System.out.println("连接成功...");

        // String filename = "/Users/westboy/Downloads/cachecloud-web.war"; // 114.8MB
        String filename = "/Users/westboy/Downloads/atlassian-jira-software-8.13.3-x64.bin"; // 404.7MB
        // String filename = "/Users/westboy/Downloads/signon.html"; // 5KB

        FileChannel fileChannel = new FileInputStream(new File(filename)).getChannel();

        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);

        long transferSize = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数：" + transferSize + "，耗时：" + (System.currentTimeMillis() - startTime) + "ms");

        // fileChannel.close();
    }
}

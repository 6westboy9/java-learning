package com.westboy.demo12_nio_zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * @author pengbo
 * @since 2021/2/25
 */
public class OldIOClient {
    public static void main(String[] args) {

        String filename = "/Users/westboy/Downloads/atlassian-jira-software-8.13.3-x64.bin"; // 404.7MB
        // String filename = "/Users/westboy/Downloads/cachecloud-web.war"; // 114.8MB
        try (Socket socket = new Socket("127.0.0.1", 8890);
             // 读取磁盘文件
             FileInputStream fileInputStream = new FileInputStream(filename);
             // 发送至服务端
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            byte[] bytes = new byte[4096];
            int total = 0;
            int readCount = 0;

            long startTime = System.currentTimeMillis();
            while ((readCount = fileInputStream.read(bytes)) >= 0) {
                total = readCount + total;
                dataOutputStream.write(bytes);
            }

            System.out.println("发送总字节数：" + total + "，耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

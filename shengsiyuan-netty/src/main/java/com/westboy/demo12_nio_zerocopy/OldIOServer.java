package com.westboy.demo12_nio_zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author pengbo
 * @since 2021/2/25
 */
public class OldIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8890);

        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] bytes = new byte[4096];
                while (true) {
                    int readCount = inputStream.read(bytes);
                    if (readCount == -1) {
                        break; // 读取后直接丢弃
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

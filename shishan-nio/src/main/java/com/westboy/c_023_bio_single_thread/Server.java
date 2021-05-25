package com.westboy.c_023_bio_single_thread;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author pengbo
 * @since 2021/1/29
 */
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        Socket socket = serverSocket.accept();

        char[] buf = new char[3];

        InputStream in = socket.getInputStream();
        InputStreamReader inReader = new InputStreamReader(in);
        int len = inReader.read(buf);

        while (len != -1) {
            String data = new String(buf, 0, len);
            System.out.println(data);
            if (data.endsWith("#")) {
                break;
            }
            len = inReader.read(buf); // 阻塞等待
        }

        OutputStream out = socket.getOutputStream();
        out.write("收到你的消息了".getBytes());

        inReader.close();
        in.close();
        out.close();
        socket.close();
    }
}

package com.westboy.c_023_bio_single_thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author pengbo
 * @since 2021/1/29
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9000);
            OutputStream out = socket.getOutputStream();
            out.write("大家好#".getBytes());

            char[] buf = new char[3];

            InputStream in = socket.getInputStream();
            InputStreamReader inReader = new InputStreamReader(in);
            int len = inReader.read(buf);
            while(len != -1) {
                String data = new String(buf, 0, len);
                System.out.println(data);
                len = inReader.read(buf);
            }

            inReader.close();
            in.close();
            out.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

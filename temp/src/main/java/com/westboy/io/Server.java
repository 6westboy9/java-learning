package com.westboy.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// 创建一个 ServerSocket 监听 8080 端口
		ServerSocket server = new ServerSocket(8080);
		// 等待请求
		Socket socket = server.accept();
		// 接收数据
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = br.readLine();
		System.out.println("received from client: " + line);
		// 发送数据
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.println("received from data: " + line);
		pw.flush();
		// 关闭资源
		pw.close();
		br.close();
		socket.close();
		server.close();
	}

}

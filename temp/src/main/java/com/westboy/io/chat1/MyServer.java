package com.westboy.io.chat1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class MyServer {

	public static List<Socket> sockets = Collections.synchronizedList(new ArrayList<>());

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(30000);
		int count = 0;
		while (true) {
			Socket s = ss.accept();
			sockets.add(s);
			System.out.println("添加客户端，总计：" + ++count);
			new Thread(new ServerThread(s)).start();
		}
	}

	static class ServerThread implements Runnable {

		Socket s = null;
		BufferedReader br = null;

		public ServerThread(Socket s) throws IOException {
			this.s = s;
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		}

		@Override
		public void run() {
			try {
				String content;
				while ((content = readFromClient()) != null) {
					for (Socket s : MyServer.sockets) {
						PrintStream ps = new PrintStream(s.getOutputStream());
						ps.println(content);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private String readFromClient() {
			try {
				return br.readLine();
			} catch (IOException e) {
				// 如果捕获到异常，则表明该 Socket 对应的客户端已经关闭
				MyServer.sockets.remove(s);
				e.printStackTrace();
			}
			return null;
		}
	}

}

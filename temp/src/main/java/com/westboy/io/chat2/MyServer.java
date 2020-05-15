package com.westboy.io.chat2;

import com.westboy.io.Server;

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

	public static MyMap<String, PrintStream> clients = new MyMap<>();

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(30000);
		int count = 0;
		while (true) {
			Socket s = ss.accept();
			System.out.println("添加客户端，总计：" + ++count);
			new Thread(new ServerThread(s)).start();
		}
	}

	static class ServerThread implements Runnable {

		Socket s = null;
		BufferedReader br = null;
		PrintStream ps = null;

		public ServerThread(Socket s) throws IOException {
			this.s = s;
		}

		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				ps = new PrintStream(s.getOutputStream());
				String line = null;
				while ((line = br.readLine()) != null) {
					if (line.startsWith(MyProtocol.USER_ROUND) && line.endsWith(MyProtocol.USER_ROUND)) {
						String userName = getRealMsg(line);
						if (MyServer.clients.map.containsKey(userName)) {
							System.out.println("重复");
							ps.println(MyProtocol.NAME_REP);
						} else {
							System.out.println("成功");
							ps.println(MyProtocol.LOGIN_SUCCESS);
							MyServer.clients.put(userName, ps);
						}
					} else if (line.startsWith(MyProtocol.PRIVATE_ROUND) && line.endsWith(MyProtocol.PRIVATE_ROUND)) {
						String userAndMsg = getRealMsg(line);
						String user = userAndMsg.split(MyProtocol.SPLIT_SIGN)[0];
						String msg = userAndMsg.split(MyProtocol.SPLIT_SIGN)[1];
						MyServer.clients.map.get(user).println(MyServer.clients.getKeyByValue(ps) + "悄悄地对你说：" + msg);
					} else {
						String msg = getRealMsg(line);
						for (PrintStream clientPs : MyServer.clients.valueSet()) {
							clientPs.println(MyServer.clients.getKeyByValue(ps) + "说：" + msg);
						}
					}
				}
			} catch (IOException e) {
				MyServer.clients.removeByValue(ps);
				System.out.println(MyServer.clients.map.size());
				e.printStackTrace();
			}
		}

		private String getRealMsg(String line) {
			return line.substring(MyProtocol.PROTOCOL_LEN, line.length() - MyProtocol.PROTOCOL_LEN);
		}
	}

}

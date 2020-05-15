package com.westboy.io.chat2;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class MyClient {

	private static final int SERVER_PORT = 30000;
	private Socket socket;
	private PrintStream ps;
	private BufferedReader brServer;
	private BufferedReader keyIn;

	public void init() {

		try {
			keyIn = new BufferedReader(new InputStreamReader(System.in));
			socket = new Socket("127.0.0.01", SERVER_PORT);
			ps = new PrintStream(socket.getOutputStream());
			brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip = "";

			while (true) {
				String userName = JOptionPane.showInputDialog(tip + "输入用户名");
				ps.println(MyProtocol.USER_ROUND + userName + MyProtocol.USER_ROUND);
				String result = brServer.readLine();
				if (result.equals(MyProtocol.NAME_REP)) {
					tip = "用户名重复，请重输！";
					continue;
				}
				if (result.equals(MyProtocol.LOGIN_SUCCESS)) {
					break;
				}
			}

		} catch (UnknownHostException e) {
			System.out.println("找不到远程服务器，请确定服务器已经启动！");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("网络异常！请重新登录！");
			e.printStackTrace();
		}

		new Thread(new ClientThread(brServer)).start();
	}


	public static void main(String[] args) throws IOException {
		MyClient client = new MyClient();
		client.init();
		client.readAndSend();
	}

	private void readAndSend() throws IOException {
		String line = null;
		while ((line = keyIn.readLine()) != null) {
			if (line.indexOf(":") > 0 && line.startsWith("//")) {
				line = line.substring(2);
				ps.println(MyProtocol.PRIVATE_ROUND + line.split(":")[0] + MyProtocol.SPLIT_SIGN + line.split(":")[1] + MyProtocol.PRIVATE_ROUND);
			} else {
				ps.println(MyProtocol.MSG_ROUND + line + MyProtocol.MSG_ROUND);
			}
		}
	}

	static class ClientThread implements Runnable {
		BufferedReader br = null;

		public ClientThread(BufferedReader br) {
			this.br = br;
		}

		@Override
		public void run() {
			try {
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}


		}
	}

}

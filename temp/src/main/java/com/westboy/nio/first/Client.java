package com.westboy.nio.first;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class Client {
	public static void main(String[] args) throws IOException {
		// 1.通过SocketChannel连接到远程服务器
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress(8888));

		// 2.创建读数据/写数据缓冲区对象来读取服务端数据并向服务端发送数据
		BufferSupport.write(sc, "hello");
		BufferSupport.read(sc);

		// 3.关闭资源
		sc.close();
	}
}

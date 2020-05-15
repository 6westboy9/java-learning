package com.westboy.nio.first;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class Server {
	public static void main(String[] args) throws IOException {
		// 通过 ServerSocketChannel#open 方法创建一个 ServerSocketChannel 对象
		ServerSocketChannel ssc = ServerSocketChannel.open();

		// 1.ssc 绑定 IP 和端口号
		ssc.socket().bind(new InetSocketAddress(8888));
		// 2.调用 ServerSocketChannel#accept 方法创建一个 SocketChannel 对象 sc 从客户端读/写数据
		// 注意：一般服务端起来之后，如果没有监听到客户端连接上，就会一直阻塞在此处
		SocketChannel sc = ssc.accept();

		// 3.创建读数据/写数据缓冲区对象来读取客户端数据或向客户端发送数据
		BufferSupport.read(sc);
		BufferSupport.write(sc, "data has been received.");

		// 4.关闭资源
		sc.close();
		ssc.close();
	}
}

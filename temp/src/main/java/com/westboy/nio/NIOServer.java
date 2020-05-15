package com.westboy.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class NIOServer {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(8080));
		// 通道必须是非阻塞，否则使用选择器就没有意义，因为如果通道在某个事件上被阻塞，那么服务器就不能响应其它事件，必须等待这个事件处理完毕才能去处理其它事件，显然这和选择器的作用背道而驰。
		ssc.configureBlocking(false);

		// 开启选择器
		Selector selector = Selector.open();
		// 注册具体的事件
		ssc.register(selector, SelectionKey.OP_ACCEPT);

		Handler handler = new Handler(1024);

		while (true) {
			// 监听事件
			if (selector.select(3000) == 0) {
				System.out.println("等待请求超时...");
				continue;
			}

			System.out.println("处理请求...");
			// 获取到达的事件
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			// 事件循环，因为一次 select 调用一般是不可能处理完所有事件，并且服务器端有可能需要一直监听事件，因此服务器端处理事件的代码一般会放在死循环内
			while (keyIter.hasNext()) {
				SelectionKey key = keyIter.next();
				// 接收到连接请求时
				if (key.isAcceptable()) {
					handler.handleAccept(key);
				}

				if (key.isReadable()) {
					handler.handleRead(key);
				}
				keyIter.remove();
			}
		}
	}

}

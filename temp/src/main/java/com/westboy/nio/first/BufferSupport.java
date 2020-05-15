package com.westboy.nio.first;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class BufferSupport {

	private static final int BUFFER_SIZE = 1024;

	public static void read(SocketChannel sc) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		sc.read(buffer);
		StringBuilder sb = new StringBuilder();
		buffer.flip();
		while (buffer.hasRemaining()) {
			sb.append((char) buffer.get());
		}
		System.out.println(sb.toString());
	}

	public static void write(SocketChannel sc, String msg) throws IOException {
		ByteBuffer buffer2 = ByteBuffer.allocate(BUFFER_SIZE);
		buffer2.put(msg.getBytes());
		buffer2.flip();
		sc.write(buffer2);
	}
}

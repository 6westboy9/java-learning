package com.westboy.class_012.demo01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf requestBuffer = (ByteBuf) msg;
		byte[] requestBytes = new byte[requestBuffer.readableBytes()];
		requestBuffer.readBytes(requestBytes);
		
		String request = new String(requestBytes, StandardCharsets.UTF_8);
		System.out.println("接收到的请求：" + request); 
		
		String response = "收到你的请求了，返回响应给你";
		ByteBuf responseBuffer = Unpooled.copiedBuffer(response.getBytes());
		ctx.write(responseBuffer);

		System.out.println(Thread.currentThread().getName());

		// 这个东西类似对应着我们之前说的那个 Processor 线程，负责读取请求，返回响应
		// 具体底层的源码还没看，这个东西也可以理解为我们之前说的那个 Handler 线程
		// Netty 底层就有类似 Processor 的东西，负责从网络连接中读取请求
		// 然后把读取出来的请求交给我们的 Handler 线程来处理，处理完以后把响应返回回去
		// 但是可能在底层响应是由 Processor 线程来发送回去的
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		System.out.println(Thread.currentThread().getName());

		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	
}

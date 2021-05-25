package com.westboy.class_012.demo01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	private final ByteBuf requestBuffer;
	
	public NettyClientHandler() {
		byte[] requestBytes = "你好，我发送第一条消息".getBytes();
		requestBuffer = Unpooled.buffer(requestBytes.length);
		requestBuffer.writeBytes(requestBytes);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println(Thread.currentThread().getName());

		ctx.writeAndFlush(requestBuffer);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf responseBuffer = (ByteBuf) msg;
		byte[] responseBytes = new byte[responseBuffer.readableBytes()];
		responseBuffer.readBytes(responseBytes);
		
		String response = new String(responseBytes, StandardCharsets.UTF_8);

		System.out.println(Thread.currentThread().getName());

		System.out.println("接收到服务端的响应：" + response);  
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}

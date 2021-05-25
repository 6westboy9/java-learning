package com.westboy.demo02_client_and_server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

import java.util.UUID;

/**
 * SimpleChannelInboundHandler 泛型中的 String 同 channelRead0 方法中 msg 类型是一致的
 *
 * @author pengbo
 * @since 2021/1/12
 */
public class Demo02MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        SocketChannel socketChannel = (SocketChannel) ctx.channel();
        System.out.println("server out: " + socketChannel.remoteAddress() + ", " + msg);
        socketChannel.writeAndFlush("from server: " + UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 出现异常时直接关闭调
        ctx.close();
    }
}

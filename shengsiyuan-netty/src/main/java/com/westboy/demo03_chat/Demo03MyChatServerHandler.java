package com.westboy.demo03_chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;

/**
 * @author pengbo
 * @since 2021/1/15
 */
public class Demo03MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // System.out.println("收到客户端消息: " + msg);
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch) {

                /*
                 * 重要！！！
                 * 服务端发送消息的时候，也是也 \n 换行符为结束标识
                 */

                ch.writeAndFlush("服务器响应 -> 来自 " + filterRemoteAddress(ch.remoteAddress())  + " 发送的消息: " + msg + "\n");
            } else {
                ch.writeAndFlush("服务器响应 -> 来自自己发送的消息: " + msg + "\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("服务器: " + filterRemoteAddress(channel.remoteAddress())  + " 加入\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("服务器: " + filterRemoteAddress(channel.remoteAddress()) + " 离开\n");
        channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(filterRemoteAddress(channel.remoteAddress()) + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(filterRemoteAddress(channel.remoteAddress()) + " 下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private String filterRemoteAddress(SocketAddress socketAddress) {
        return socketAddress.toString().substring(1);
    }
}

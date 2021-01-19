package com.westboy.demo03;

import com.westboy.demo02.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author pengbo
 * @since 2021/1/15
 */
public class MyChatClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        // 不同于服务端的 ServerBootstrap
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                // 不同于服务端的 NioServerSocketChannel
                .channel(NioSocketChannel.class)
                .handler(new MyChatClientInitializer());

        try {
            Channel channel = bootstrap.connect("localhost", 8888).sync().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // 等待客户端发送消息
            for (; ; ) {
                // 这里的 \n 很重要，在 MyChatClientInitializer 中，添加了 Delimiters.lineDelimiter() 行分隔符
                // 当你输入后，敲击回车后，Netty 客户端识别到 \n 换行符之后会发送过去
                // 同理，服务端发送消息的时候，也是也 \n 换行符为结束标识
                channel.writeAndFlush(br.readLine() + "\r\n");
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                eventLoopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

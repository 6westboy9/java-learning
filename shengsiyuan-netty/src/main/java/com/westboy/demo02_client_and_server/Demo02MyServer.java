package com.westboy.demo02_client_and_server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author pengbo
 * @since 2021/1/12
 */
public class Demo02MyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .localAddress(new InetSocketAddress(8888))
                .channel(NioServerSocketChannel.class)
                .childHandler(new Demo02MyServerInitializer());

        try {
            // 绑定服务器
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("服务启动成功，监听端口：" + channelFuture.channel().localAddress());
            // 等待通道关闭的异步任务结束
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                bossGroup.shutdownGracefully().sync();
                workerGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

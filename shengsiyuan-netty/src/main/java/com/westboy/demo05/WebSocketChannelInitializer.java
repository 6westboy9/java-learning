package com.westboy.demo05;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author pengbo
 * @since 2021/1/18
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // HttpServerCodec
        pipeline.addLast(new HttpServerCodec());
        // ChunkedWriteHandler
        pipeline.addLast(new ChunkedWriteHandler());
        // HttpObjectAggregator 用来分段聚合
        pipeline.addLast(new HttpObjectAggregator(8192));

        // 以上都是有关 HTTP 请求处理器
        // 以下是 WebSocket 请求处理器

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebsocketFrameHandler());
    }
}

package com.westboy.demo04_idle_state;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author pengbo
 * @since 2021/1/12
 */
public class Demo04MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 读超时，即当在指定的时间间隔内没有从 Channel 读取到数据时，会触发一个 READER_IDLE 的 IdleStateEvent 事件
        // 写超时，即当在指定的时间间隔内没有数据写入到 Channel 时，会触发一个 WRITER_IDLE 的 IdleStateEvent 事件
        // 读/写超时，即当在指定的时间间隔内没有读或写操作时，会触发一个 ALL_IDLE 的 IdleStateEvent 事件
        pipeline.addLast(new IdleStateHandler(3, 4, 5, TimeUnit.SECONDS));
        pipeline.addLast(new Demo04MyServerHandler());
    }
}

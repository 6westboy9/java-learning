package com.westboy.demo03_chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author pengbo
 * @since 2021/1/15
 */
public class Demo03MyChatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 4096 长度限定，因为可能我收到太多的数据都没有遇到行分隔符（这里使用行分隔来进行粘包拆包）
        // 所以，缓存太多的话可能导致内存溢出的情况，所以可以根据实际包的大小指定一个上限
        // 就是在这个上限内一定存在粘包拆包分隔符
        pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new Demo03MyChatServerHandler());
    }
}

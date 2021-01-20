package com.westboy.demo04_idle_state;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * SimpleChannelInboundHandler 泛型中的 String 同 channelRead0 方法中 msg 类型是一致的
 *
 * @author pengbo
 * @since 2021/1/12
 */
public class Demo04MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 读超时，即当在指定的时间间隔内没有从 Channel 读取到数据时，会触发一个 READER_IDLE 的 IdleStateEvent 事件
        // 写超时，即当在指定的时间间隔内没有数据写入到 Channel 时，会触发一个 WRITER_IDLE 的 IdleStateEvent 事件
        // 读/写超时，即当在指定的时间间隔内没有读或写操作时，会触发一个 ALL_IDLE 的 IdleStateEvent 事件
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null;
            switch (event.state()) {
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + " 超时事件: " + eventType);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 出现异常时直接关闭调
        ctx.close();
    }
}

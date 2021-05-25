package com.westboy.demo07_protobuf_in_netty.client;

import com.westboy.demo07_protobuf_in_netty.MyMessageInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author westboy
 * @date 2018-12-11
 * @since 1.0
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyMessageInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageInfo.MyMessage msg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        int randomInt = new Random().nextInt(3);
        System.out.println(randomInt);
        MyMessageInfo.MyMessage myMessage;

        if (0 == randomInt) {
            myMessage = MyMessageInfo.MyMessage.newBuilder().
                    setDataType(MyMessageInfo.MyMessage.DataType.PersonType).
                    setPerson(MyMessageInfo.Person.newBuilder().
                            setName("zhangsan").
                            setAge(20).
                            setAddress("beijing").
                            build()).
                    build();
        } else if (1 == randomInt) {
            myMessage = MyMessageInfo.MyMessage.newBuilder().
                    setDataType(MyMessageInfo.MyMessage.DataType.DogType).
                    setDog(MyMessageInfo.Dog.newBuilder().
                            setName("dog").
                            setAge(21).
                            build()).
                    build();
        } else {
            myMessage = MyMessageInfo.MyMessage.newBuilder().
                    setDataType(MyMessageInfo.MyMessage.DataType.CatType).
                    setCat(MyMessageInfo.Cat.newBuilder().
                            setName("cat").
                            setCity("shanghai").
                            build()).
                    build();
        }

        ctx.channel().writeAndFlush(myMessage);
    }
}
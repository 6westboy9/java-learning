package com.westboy.demo07_protobuf_in_netty.server;

import com.westboy.demo07_protobuf_in_netty.MyMessageInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author westboy
 * @date 2018-12-11
 * @since 1.0
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyMessageInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageInfo.MyMessage msg) {
        MyMessageInfo.MyMessage.DataType dateType = msg.getDataType();

        if (dateType == MyMessageInfo.MyMessage.DataType.PersonType) {
            MyMessageInfo.Person person = msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        } else if (dateType == MyMessageInfo.MyMessage.DataType.DogType) {
            MyMessageInfo.Dog dog = msg.getDog();
            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        } else {
            MyMessageInfo.Cat cat = msg.getCat();
            System.out.println(cat.getName());
            System.out.println(cat.getCity());
        }
    }
}

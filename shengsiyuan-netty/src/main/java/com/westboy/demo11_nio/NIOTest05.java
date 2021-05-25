package com.westboy.demo11_nio;

import java.nio.ByteBuffer;

/**
 * @author pengbo
 * @since 2021/2/6
 */
public class NIOTest05 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(5000000000L);
        buffer.putDouble(14.13333);
        buffer.putChar('你');
        buffer.putShort(Short.parseShort("2"));
        buffer.putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}

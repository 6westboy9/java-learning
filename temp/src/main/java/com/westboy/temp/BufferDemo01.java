package com.westboy.temp;

import java.nio.ByteBuffer;

public class BufferDemo01 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putInt(1);
        buffer.flip();
        System.out.println(buffer.getInt());
    }
}

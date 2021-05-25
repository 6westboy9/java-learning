package com.westboy.demo11_nio;

import java.nio.ByteBuffer;

/**
 * @author pengbo
 * @since 2021/2/6
 */
public class NIOTest07 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        System.out.println(buffer);         // java.nio.HeapByteBuffer[pos=10 lim=10 cap=10]
        System.out.println(readOnlyBuffer); // java.nio.HeapByteBufferR[pos=10 lim=10 cap=10]，注意其类型为 HeapByteBufferR

        readOnlyBuffer.put(0, (byte)2); // 抛出异常 java.nio.ReadOnlyBufferException

    }
}

package com.westboy.demo11_nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author pengbo
 * @since 2021/1/27
 */
public class NIOTest01 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(new SecureRandom().nextInt(20));
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}

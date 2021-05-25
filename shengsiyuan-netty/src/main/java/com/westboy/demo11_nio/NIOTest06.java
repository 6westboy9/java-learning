package com.westboy.demo11_nio;

import java.nio.ByteBuffer;

/**
 * @author pengbo
 * @since 2021/2/6
 */
public class NIOTest06 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        // 打印 buffer
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.print(buffer.get(i) + " ");
        }

        System.out.println();

        buffer.position(2);
        buffer.limit(6);

        // 左闭右开，共享原有 buffer 的数据
        ByteBuffer slice = buffer.slice();
        System.out.println(slice);
        // 打印 slice
        for (int i = 0; i < slice.capacity(); i++) {
            System.out.print(slice.get(i) + " ");
        }

        System.out.println();

        // 修改 slice
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 2;
            slice.put(i, b);
        }

        System.out.println(buffer);

        buffer.position(0);
        buffer.limit(buffer.capacity());
        for (int i = 0; i < buffer.capacity(); i++) {
            // 0 1 4 6 8 10 6 7 8 9
            // 0 1 2 3 4 5  6 7 8 9
            // 左闭右开，所以 slice 改变的值为 2 3 4 5
            System.out.print(buffer.get() + " ");
        }
    }
}

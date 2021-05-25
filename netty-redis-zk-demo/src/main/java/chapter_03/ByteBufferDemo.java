package chapter_03;

import java.nio.ByteBuffer;

/**
 * @author pengbo
 * @since 2021/1/26
 */
public class ByteBufferDemo {
    public static void main(String[] args) {

        byte[] bytes = {1, 2, 3, 4};
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer.position());
        System.out.println(buffer);

    }
}

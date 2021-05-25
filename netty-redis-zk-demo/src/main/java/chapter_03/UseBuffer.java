package chapter_03;

import java.nio.IntBuffer;

/**
 * @author pengbo
 * @since 2021/1/26
 */
public class UseBuffer {

    public static void main(String[] args) {
        // 调用 allocate 方法，而不是使用 new
        IntBuffer intBuffer = IntBuffer.allocate(20);

        System.out.println("-----------------after allocate-----------------");
        print(intBuffer);

        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }

        System.out.println("-----------------after put-----------------");
        print(intBuffer);

        // 翻转缓冲区，从写模式翻转为读模式
        intBuffer.flip();
        System.out.println("-----------------after put-----------------");
        print(intBuffer);

        for (int i = 0; i < 2; i++) {
            int j = intBuffer.get();
            System.out.println("j=" + j);
        }
        System.out.println("-----------------after get 2 int-----------------");
        print(intBuffer);

        for (int i = 0; i < 3; i++) {
            int j = intBuffer.get();
            System.out.println("j=" + j);
        }
        System.out.println("-----------------after get 3 int-----------------");
        print(intBuffer);


        intBuffer.rewind();
        System.out.println("-----------------after rewind-----------------");
        print(intBuffer);

        for (int i = 0; i < 5; i++) {
            // 临时保存，标记一下第 3 个位置
            if (i == 2) {
                intBuffer.mark();
            }
            int j = intBuffer.get();
            System.out.println("j=" + j);
        }
        System.out.println("-----------------after reRead-----------------");
        print(intBuffer);


        intBuffer.reset();
        System.out.println("-----------------after reset-----------------");
        print(intBuffer);

        for (int i = 2; i < 5; i++) {
            int j = intBuffer.get();
            System.out.println("j=" + j);
        }

        intBuffer.clear();
        System.out.println("-----------------after clear-----------------");
        print(intBuffer);
    }

    private static void print(IntBuffer intBuffer) {
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
    }
}

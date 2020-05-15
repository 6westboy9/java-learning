package com.westboy.io.buffer;

import java.nio.CharBuffer;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class BufferTest {

	public static void main(String[] args) {
		CharBuffer buff = CharBuffer.allocate(8);
		System.out.println("初始 capacity: " + buff.capacity());
		System.out.println("初始 limit: " + buff.limit());
		System.out.println("初始 position: " + buff.position());

		buff.put('a');
		buff.put('b');
		buff.put('c');
		System.out.println();
		System.out.println("写入数据后, position: " + buff.position());
		buff.flip();

		System.out.println();
		System.out.println("切换读模式后, limit: " + buff.limit());
		System.out.println("切换读模式后, position: " + buff.position());
		System.out.println("切换读模式后, 取出索引为 1 的元素数据: " + buff.get());
		System.out.println("切换读模式后, 取出索引为 1 的元素数据后, position: " + buff.position());

		buff.clear();
		System.out.println();
		System.out.println("重置后, limit: " + buff.limit());
		System.out.println("重置后, position: " + buff.position());
		System.out.println("重置后, 取出索引为 2 的元素数据: " + buff.get(2));
		System.out.println("重置后, 取出索引为 2 的元素数据后, position: " + buff.position());
		buff.put(3, 'd');
		System.out.println("重置后, 取出索引为 3 的元素数据: " + buff.get(3));
		System.out.println("重置后, 取出索引为 3 的元素数据后, position: " + buff.position());
		System.out.println("重置后, 取出索引为 3 的元素数据后, limit: " + buff.limit());

	}
}

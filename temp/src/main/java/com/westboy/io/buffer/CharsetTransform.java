package com.westboy.io.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class CharsetTransform {

	public static void main(String[] args) throws CharacterCodingException {
		Charset cn = Charset.defaultCharset();
		// System.out.println(cn);
		CharsetEncoder encoder = cn.newEncoder();
		CharsetDecoder decoder = cn.newDecoder();

		CharBuffer buffer = CharBuffer.allocate(8);
		buffer.put('孙');
		buffer.put('悟');
		buffer.put('空');

		buffer.flip();


		ByteBuffer byteBuffer = cn.encode(buffer);
		System.out.println(byteBuffer.capacity());
		for (int i = 0; i < byteBuffer.capacity(); i++) {
			System.out.print(byteBuffer.get(i) + " ");
		}

		System.out.println("\n" + cn.decode(byteBuffer));
	}
}

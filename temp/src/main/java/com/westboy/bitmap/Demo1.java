package com.westboy.bitmap;

import com.googlecode.javaewah.EWAHCompressedBitmap;

/**
 * @author westboy
 * @since 2019/12/4
 */
public class Demo1 {

	public static void main(String[] args) {
		EWAHCompressedBitmap bitmap = new EWAHCompressedBitmap();
		bitmap.set(1);
		bitmap.set(2);
		bitmap.forEach(bm -> System.out.println(bm.toString()));
	}
}

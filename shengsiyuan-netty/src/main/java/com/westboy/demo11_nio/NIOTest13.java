package com.westboy.demo11_nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author pengbo
 * @since 2021/2/23
 */
public class NIOTest13 {
    public static void main(String[] args) throws Exception {
        String inputFile = "shengsiyuan-netty-tmp/NIOTest13_In.txt";
        String outputFile = "shengsiyuan-netty-tmp/NIOTest13_Out.txt";
        RandomAccessFile  inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");


        long inputLength = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        // 查看当前操作系统默认字符集
        System.out.println("===========================");
        System.out.println(Charset.defaultCharset());
        // 查看当前操作系统所支持的字符集
        System.out.println("===========================");
        Charset.availableCharsets().forEach((k,v) -> System.out.println(k +", " + v));
        System.out.println("===========================");

        Charset charset = StandardCharsets.UTF_8;
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inputData);
        ByteBuffer byteBuffer = encoder.encode(charBuffer);

        outputFileChannel.write(byteBuffer);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();
    }
}

package com.westboy.c_031_nio_client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NIOClient {

    private static final CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
    private static final CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

    public static void main(String[] args) {
        // for (int i = 0; i < 10; i++) {
            new Worker().start();
        // }
    }

    static class Worker extends Thread {

        @Override
        public void run() {
            SocketChannel channel = null;
            Selector selector = null;
            try {
                channel = SocketChannel.open();
                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress("localhost", 9000));

                selector = Selector.open();
                channel.register(selector, SelectionKey.OP_CONNECT);

                while (true) {
                    selector.select(1000);

                    Set<SelectionKey> keys = selector.selectedKeys();

                    printKeys(keys);

                    Iterator<SelectionKey> keysIterator = keys.iterator();
                    while (keysIterator.hasNext()) {
                        SelectionKey key = keysIterator.next();
                        keysIterator.remove();

                        if (key.isConnectable()) {
                            if (channel.isConnectionPending()) {

                                while (!channel.finishConnect()) {
                                    Thread.sleep(100);
                                }
                                // if (channel.finishConnect()) {

                                ByteBuffer buffer = encoder.encode(CharBuffer.wrap("你好"));
                                while (channel.write(buffer) > 0) {
                                }

                                key.interestOps(SelectionKey.OP_READ);
                                // }
                            }
                        } else if (key.isReadable()) {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(128);

                            int read;
                            int count = 0;
                            while ((read = channel.read(byteBuffer)) > 0) {
                                count += read;
                            }

                            if (count > 0) {
                                byteBuffer.flip();
                                CharBuffer charBuffer = decoder.decode(byteBuffer);
                                String response = charBuffer.toString();
                                System.out.println("[" + Thread.currentThread().getName() + "]收到响应：" + response + "__________________" + byteBuffer.remaining());

                                // channel.write(encoder.encode(CharBuffer.wrap("你好")));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (selector != null) {
                    try {
                        selector.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private static void printKeys(Set<SelectionKey> keys) {
            Map<String, Integer> map = new HashMap<>();
            for (SelectionKey key : keys) {
                if (key.isConnectable()) {
                    Integer count = map.getOrDefault("isConnectable", 1);
                    map.put("isConnectable", count);
                }
                if (key.isWritable()) {
                    Integer count = map.getOrDefault("isWritable", 1);
                    map.put("isWritable", count);
                }

                if (key.isReadable()) {
                    Integer count = map.getOrDefault("isReadable", 1);
                    map.put("isReadable", count);
                }

                if (key.isAcceptable()) {
                    Integer count = map.getOrDefault("isAcceptable", 1);
                    map.put("isAcceptable", count);
                }

                if (key.isValid()) {
                    Integer count = map.getOrDefault("isValid", 1);
                    map.put("isValid", count);
                }
            }

            System.out.println(keys.size() + ":" + map);
        }
    }

}

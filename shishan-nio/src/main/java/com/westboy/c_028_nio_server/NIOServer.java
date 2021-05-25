package com.westboy.c_028_nio_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NIOServer {

    private static final CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
    private static final CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

    private static Selector selector;

    public static void main(String[] args) {
        init();
        listen();
    }

    private static void init() {
        ServerSocketChannel servSocketChannel;
        try {
            servSocketChannel = ServerSocketChannel.open();
            servSocketChannel.configureBlocking(false);
            servSocketChannel.socket().bind(new InetSocketAddress(9000), 100);
            selector = Selector.open();
            servSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listen() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();

                printKeys(keys);

                Iterator<SelectionKey> keysIterator = keys.iterator();
                while (keysIterator.hasNext()) {
                    SelectionKey key = keysIterator.next();
                    keysIterator.remove();
                    handleKey(key);
                }
            } catch (Throwable t) {
                t.printStackTrace();
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

    private static void handleKey(SelectionKey key) throws IOException {
        SocketChannel channel = null;
        try {
            if (key.isAcceptable()) {
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                channel = serverChannel.accept();
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                channel = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1034);

                int read;
                int count = 0;
                while ((read = channel.read(readBuffer)) > 0) {
                    count += read;
                }

                if (count > 0) {
                    readBuffer.flip();
                    CharBuffer charBuffer = decoder.decode(readBuffer);
                    String request = charBuffer.toString();
                    System.out.println(request + "__________________" + readBuffer.remaining());
                    String response = "返回响应：" + channel.getRemoteAddress();
                    channel.write(encoder.encode(CharBuffer.wrap(response)));
                }

                // else {
                //     channel.close();
                // }
            }
        } catch (Throwable t) {
            t.printStackTrace();
            if (channel != null) {
                channel.close();
            }
        }
    }

}

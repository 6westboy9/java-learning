package com.westboy.demo09_grpc.demo02;

import com.google.protobuf.ByteString;
import com.westboy.demo09_grpc.generated.FileServiceGrpc;
import com.westboy.demo09_grpc.generated.Request;
import com.westboy.demo09_grpc.generated.Response;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client(HOST, PORT);
        String filepath = "/Users/westboy/IdeaProjects/personal/java-learning/shengsiyuan-netty-tmp/NIOTest02.txt";
        client.upload("NIOTest02.txt", filepath);
        client.shutdown();
    }

    private final ManagedChannel managedChannel;
    private final FileServiceGrpc.FileServiceBlockingStub blockingStub;

    public Client(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    /**
     * 上传文件
     *
     * @param name 保存到服务端的文件名
     * @param path 要上传的文件路径
     */
    public void upload(String name, String path) throws IOException {
        Request request = Request.newBuilder()
                .setName(name)
                // 文件 -> 字节码数据 -> ByteString
                .setFile(ByteString.copyFrom(Objects.requireNonNull(getContent(path))))
                .build();
        Response response;
        try {
            response = blockingStub.upload(request);
            System.out.println(response.getMsg());
        } catch (StatusRuntimeException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 关闭客户端
     */
    public void shutdown() throws InterruptedException {
        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    Client(ManagedChannelBuilder<?> channelBuilder) {
        managedChannel = channelBuilder.build();
        blockingStub = FileServiceGrpc.newBlockingStub(managedChannel);
    }


    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fi.close();
        System.out.println("生成文件长度" + buffer.length);
        return buffer;
    }
}

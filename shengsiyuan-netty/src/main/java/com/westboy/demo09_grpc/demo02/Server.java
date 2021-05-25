package com.westboy.demo09_grpc.demo02;

import cn.hutool.core.io.FileUtil;
import com.westboy.demo09_grpc.generated.FileServiceGrpc;
import com.westboy.demo09_grpc.generated.Request;
import com.westboy.demo09_grpc.generated.Response;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Server {
    private io.grpc.Server server;
    private static final int PORT = 8888;


    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        server.start(PORT);
        server.await();
    }

    private void start(int port) throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new BasicCalImpl())
                .build()
                .start();
        // 添加钩子,在程序关闭时自动关闭服务端
        addHook();
    }

    private void addHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("监听到JVM停止，正在关闭GRPC服务");
            this.stop();
            System.out.println("服务已经停止");
        }));
    }

    /**
     * 关闭服务
     */
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void await() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class BasicCalImpl extends FileServiceGrpc.FileServiceImplBase {
        @Override
        public void upload(Request request, StreamObserver<Response> responseObserver) {
            byte[] bytes = request.getFile().toByteArray();
            System.out.printf("收到文件%s长度%s%n", request.getName(), bytes.length);
            String dirPath = "/Users/westboy/IdeaProjects/personal/java-learning/shengsiyuan-netty-tmp/demo09_grpc/demo02/";
            FileUtil.mkdir(dirPath);

            File f = new File(dirPath + request.getName());
            Response response;
            FileUtil.del(f);
            try (OutputStream os = new FileOutputStream(f)) {
                os.write(bytes);
                response = Response.newBuilder().setCode(1).setMsg("上传成功").build();
            } catch (IOException e) {
                response = Response.newBuilder().setCode(-1).setMsg(String.format("上传失败:%s", e.getMessage())).build();
                e.printStackTrace();
            }
            // 返回数据，完成此次请求
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}

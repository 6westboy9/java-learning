package com.westboy.demo09_grpc.demo01.client;

import com.westboy.demo09_grpc.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * @author pengbo
 * @since 2021/1/27
 */
public class GrpcClient {

    private static final ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 8899)
            .usePlaintext()
            .build();

    private static final StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(channel);


    public static void main(String[] args) throws Exception {
        // 简单模式
        test1();
        // 服务端数据流模式
        // test2();
        // 客户端数据流模式
        // test3();
        // 双向数据流模式
        // test4();
    }

    private static void test1() {
        // ① 第一种情况
        // 第一次会建立连接，请求时间较后续执行较长
        MyResponse response1 = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
        System.out.println(response1.getRealname());

        // 复用连接，很快
        MyResponse response2 = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
        System.out.println(response2.getRealname());
    }

    /**
     * 流式响应（单向流）
     */
    private static void test2() {
        // ② 服务端流式响应对应客户端就是一个迭代器
        Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        while (iterator.hasNext()) {
            StudentResponse response = iterator.next();
            System.out.println(response.getName() + ", " + response.getAge() + ", " + response.getCity());
        }
    }

    /**
     * 流式请求（单向流）
     */
    private static void test3() throws InterruptedException {
        // ③ 客户端发送流式请求
        StreamObserver<StudentResponseList> responseListStreamObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList responseList) {
                responseList.getStudentResponseList().forEach(response -> {
                    System.out.println(response.getName() + ", " + response.getAge() + ", " + response.getCity());
                });
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("throwable message：" + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        };

        // 需要使用 StudentServiceStub，不同于上面的 StudentServiceBlockingStub
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(channel);
        StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentsWrapperByAges(responseListStreamObserver);

        for (int i = 0; i < 10; i++) {
            TimeUnit.MILLISECONDS.sleep(500);
            studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
            System.out.println("1111111111");
        }

        studentRequestStreamObserver.onCompleted();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * 流式请求与响应
     */
    private static void test4() throws InterruptedException {
        // 需要使用 StudentServiceStub，不同于上面的 StudentServiceBlockingStub
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(channel);

        TimeUnit.SECONDS.sleep(5);

        StreamObserver<StreamRequest> streamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse streamResponse) {
                System.out.println(streamResponse.getResponseInfo());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("throwable message：" + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        });


        for (int i = 0; i < 10; i++) {
            streamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
            // 每隔两秒进行一次发送
            TimeUnit.SECONDS.sleep(2);
        }

        streamObserver.onCompleted();

        TimeUnit.SECONDS.sleep(50);
    }




}
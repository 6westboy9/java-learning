package com.westboy.demo09_grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.35.0)",
    comments = "Source: Student.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.westboy.demo09_grpc.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.MyRequest,
      com.westboy.demo09_grpc.generated.MyResponse> getGetRealNameByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRealNameByUsername",
      requestType = com.westboy.demo09_grpc.generated.MyRequest.class,
      responseType = com.westboy.demo09_grpc.generated.MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.MyRequest,
      com.westboy.demo09_grpc.generated.MyResponse> getGetRealNameByUsernameMethod() {
    io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.MyRequest, com.westboy.demo09_grpc.generated.MyResponse> getGetRealNameByUsernameMethod;
    if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
          StudentServiceGrpc.getGetRealNameByUsernameMethod = getGetRealNameByUsernameMethod =
              io.grpc.MethodDescriptor.<com.westboy.demo09_grpc.generated.MyRequest, com.westboy.demo09_grpc.generated.MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRealNameByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westboy.demo09_grpc.generated.MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westboy.demo09_grpc.generated.MyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetRealNameByUsername"))
              .build();
        }
      }
    }
    return getGetRealNameByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.StudentRequest,
      com.westboy.demo09_grpc.generated.StudentResponse> getGetStudentsByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsByAge",
      requestType = com.westboy.demo09_grpc.generated.StudentRequest.class,
      responseType = com.westboy.demo09_grpc.generated.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.StudentRequest,
      com.westboy.demo09_grpc.generated.StudentResponse> getGetStudentsByAgeMethod() {
    io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.StudentRequest, com.westboy.demo09_grpc.generated.StudentResponse> getGetStudentsByAgeMethod;
    if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentsByAgeMethod = getGetStudentsByAgeMethod =
              io.grpc.MethodDescriptor.<com.westboy.demo09_grpc.generated.StudentRequest, com.westboy.demo09_grpc.generated.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStudentsByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westboy.demo09_grpc.generated.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westboy.demo09_grpc.generated.StudentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsByAge"))
              .build();
        }
      }
    }
    return getGetStudentsByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.StudentRequest,
      com.westboy.demo09_grpc.generated.StudentResponseList> getGetStudentsWrapperByAgesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsWrapperByAges",
      requestType = com.westboy.demo09_grpc.generated.StudentRequest.class,
      responseType = com.westboy.demo09_grpc.generated.StudentResponseList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.StudentRequest,
      com.westboy.demo09_grpc.generated.StudentResponseList> getGetStudentsWrapperByAgesMethod() {
    io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.StudentRequest, com.westboy.demo09_grpc.generated.StudentResponseList> getGetStudentsWrapperByAgesMethod;
    if ((getGetStudentsWrapperByAgesMethod = StudentServiceGrpc.getGetStudentsWrapperByAgesMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsWrapperByAgesMethod = StudentServiceGrpc.getGetStudentsWrapperByAgesMethod) == null) {
          StudentServiceGrpc.getGetStudentsWrapperByAgesMethod = getGetStudentsWrapperByAgesMethod =
              io.grpc.MethodDescriptor.<com.westboy.demo09_grpc.generated.StudentRequest, com.westboy.demo09_grpc.generated.StudentResponseList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStudentsWrapperByAges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westboy.demo09_grpc.generated.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westboy.demo09_grpc.generated.StudentResponseList.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsWrapperByAges"))
              .build();
        }
      }
    }
    return getGetStudentsWrapperByAgesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.StreamRequest,
      com.westboy.demo09_grpc.generated.StreamResponse> getBiTalkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BiTalk",
      requestType = com.westboy.demo09_grpc.generated.StreamRequest.class,
      responseType = com.westboy.demo09_grpc.generated.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.StreamRequest,
      com.westboy.demo09_grpc.generated.StreamResponse> getBiTalkMethod() {
    io.grpc.MethodDescriptor<com.westboy.demo09_grpc.generated.StreamRequest, com.westboy.demo09_grpc.generated.StreamResponse> getBiTalkMethod;
    if ((getBiTalkMethod = StudentServiceGrpc.getBiTalkMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getBiTalkMethod = StudentServiceGrpc.getBiTalkMethod) == null) {
          StudentServiceGrpc.getBiTalkMethod = getBiTalkMethod =
              io.grpc.MethodDescriptor.<com.westboy.demo09_grpc.generated.StreamRequest, com.westboy.demo09_grpc.generated.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BiTalk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westboy.demo09_grpc.generated.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.westboy.demo09_grpc.generated.StreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("BiTalk"))
              .build();
        }
      }
    }
    return getBiTalkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub>() {
        @java.lang.Override
        public StudentServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceStub(channel, callOptions);
        }
      };
    return StudentServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub>() {
        @java.lang.Override
        public StudentServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceBlockingStub(channel, callOptions);
        }
      };
    return StudentServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub>() {
        @java.lang.Override
        public StudentServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceFutureStub(channel, callOptions);
        }
      };
    return StudentServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRealNameByUsername(com.westboy.demo09_grpc.generated.MyRequest request,
        io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.MyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRealNameByUsernameMethod(), responseObserver);
    }

    /**
     * <pre>
     * 流式响应（单向流）
     * </pre>
     */
    public void getStudentsByAge(com.westboy.demo09_grpc.generated.StudentRequest request,
        io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StudentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStudentsByAgeMethod(), responseObserver);
    }

    /**
     * <pre>
     * 流式请求（单向流）
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StudentRequest> getStudentsWrapperByAges(
        io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StudentResponseList> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getGetStudentsWrapperByAgesMethod(), responseObserver);
    }

    /**
     * <pre>
     * 流式请求与响应（双向流）
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StreamResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getBiTalkMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUsernameMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.westboy.demo09_grpc.generated.MyRequest,
                com.westboy.demo09_grpc.generated.MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USERNAME)))
          .addMethod(
            getGetStudentsByAgeMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.westboy.demo09_grpc.generated.StudentRequest,
                com.westboy.demo09_grpc.generated.StudentResponse>(
                  this, METHODID_GET_STUDENTS_BY_AGE)))
          .addMethod(
            getGetStudentsWrapperByAgesMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                com.westboy.demo09_grpc.generated.StudentRequest,
                com.westboy.demo09_grpc.generated.StudentResponseList>(
                  this, METHODID_GET_STUDENTS_WRAPPER_BY_AGES)))
          .addMethod(
            getBiTalkMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                com.westboy.demo09_grpc.generated.StreamRequest,
                com.westboy.demo09_grpc.generated.StreamResponse>(
                  this, METHODID_BI_TALK)))
          .build();
    }
  }

  /**
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractAsyncStub<StudentServiceStub> {
    private StudentServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRealNameByUsername(com.westboy.demo09_grpc.generated.MyRequest request,
        io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.MyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 流式响应（单向流）
     * </pre>
     */
    public void getStudentsByAge(com.westboy.demo09_grpc.generated.StudentRequest request,
        io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StudentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetStudentsByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 流式请求（单向流）
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StudentRequest> getStudentsWrapperByAges(
        io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StudentResponseList> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getGetStudentsWrapperByAgesMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 流式请求与响应（双向流）
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StreamResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getBiTalkMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.westboy.demo09_grpc.generated.MyResponse getRealNameByUsername(com.westboy.demo09_grpc.generated.MyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetRealNameByUsernameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 流式响应（单向流）
     * </pre>
     */
    public java.util.Iterator<com.westboy.demo09_grpc.generated.StudentResponse> getStudentsByAge(
        com.westboy.demo09_grpc.generated.StudentRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetStudentsByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractFutureStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.westboy.demo09_grpc.generated.MyResponse> getRealNameByUsername(
        com.westboy.demo09_grpc.generated.MyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USERNAME = 0;
  private static final int METHODID_GET_STUDENTS_BY_AGE = 1;
  private static final int METHODID_GET_STUDENTS_WRAPPER_BY_AGES = 2;
  private static final int METHODID_BI_TALK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USERNAME:
          serviceImpl.getRealNameByUsername((com.westboy.demo09_grpc.generated.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.MyResponse>) responseObserver);
          break;
        case METHODID_GET_STUDENTS_BY_AGE:
          serviceImpl.getStudentsByAge((com.westboy.demo09_grpc.generated.StudentRequest) request,
              (io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StudentResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STUDENTS_WRAPPER_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudentsWrapperByAges(
              (io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StudentResponseList>) responseObserver);
        case METHODID_BI_TALK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biTalk(
              (io.grpc.stub.StreamObserver<com.westboy.demo09_grpc.generated.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.westboy.demo09_grpc.generated.StudentProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUsernameMethod())
              .addMethod(getGetStudentsByAgeMethod())
              .addMethod(getGetStudentsWrapperByAgesMethod())
              .addMethod(getBiTalkMethod())
              .build();
        }
      }
    }
    return result;
  }
}

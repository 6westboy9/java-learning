// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FileService.proto

package com.westboy.demo09_grpc.generated;

public final class FileServer {
  private FileServer() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_westboy_demo09_grpc_Request_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_westboy_demo09_grpc_Request_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_westboy_demo09_grpc_Response_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_westboy_demo09_grpc_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021FileService.proto\022\027com.westboy.demo09_" +
      "grpc\"%\n\007Request\022\014\n\004file\030\001 \001(\014\022\014\n\004name\030\002 " +
      "\001(\t\"%\n\010Response\022\014\n\004code\030\001 \001(\005\022\013\n\003msg\030\002 \001" +
      "(\t2^\n\013FileService\022O\n\006Upload\022 .com.westbo" +
      "y.demo09_grpc.Request\032!.com.westboy.demo" +
      "09_grpc.Response\"\000B1\n!com.westboy.demo09" +
      "_grpc.generatedB\nFileServerP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_westboy_demo09_grpc_Request_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_westboy_demo09_grpc_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_westboy_demo09_grpc_Request_descriptor,
        new java.lang.String[] { "File", "Name", });
    internal_static_com_westboy_demo09_grpc_Response_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_westboy_demo09_grpc_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_westboy_demo09_grpc_Response_descriptor,
        new java.lang.String[] { "Code", "Msg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

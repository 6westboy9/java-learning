// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FileService.proto

package com.westboy.demo09_grpc.generated;

public interface RequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.westboy.demo09_grpc.Request)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 定义文件为字节类型
   * </pre>
   *
   * <code>bytes file = 1;</code>
   * @return The file.
   */
  com.google.protobuf.ByteString getFile();

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();
}

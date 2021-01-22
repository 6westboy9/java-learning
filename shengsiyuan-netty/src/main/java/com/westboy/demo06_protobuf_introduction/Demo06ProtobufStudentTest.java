package com.westboy.demo06_protobuf_introduction;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author pengbo
 * @since 2021/1/20
 */
public class Demo06ProtobufStudentTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student1 = DataInfo.Student.newBuilder().setName("张三").setAge(20).setAddress("北京").build();

        byte[] student2ByteArray = student1.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(student2);
        System.out.println("---");
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}

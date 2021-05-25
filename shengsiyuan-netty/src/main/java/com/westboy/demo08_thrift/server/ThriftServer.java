package com.westboy.demo08_thrift.server;

import com.westboy.demo08_thrift.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * @author westboy
 * @date 2018-12-15
 * @since 1.0
 */
public class ThriftServer {

    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        // 高可用 Server：THsHaServer
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        // 需要与客户端保持一致
        // 协议层 TCompactProtocol 二进制压缩协议
        arg.protocolFactory(new TCompactProtocol.Factory());
        // 传输层
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        // 服务
        TServer server = new THsHaServer(arg);

        System.out.println("Thrift Server Started!");

        // 异步非阻塞（死循环）
        server.serve();
    }
}

package com.westboy.demo08_thrift.client;

import com.westboy.demo08_thrift.generated.Person;
import com.westboy.demo08_thrift.generated.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author westboy
 * @date 2018-12-15
 * @since 1.0
 */
public class ThriftClient {

    public static void main(String[] args) {
        // 需要同服务端一致
        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            // 0.打开Socket
            transport.open();

            // 1.远程调用查询
            Person person = client.getPersonByUserName("zhangsan");
            System.out.println("Got from Server: " + person);
            System.out.println("-------------------");

            // 2.远程调用保存
            Person person2 = new Person();
            person2.setUsername("李四");
            person2.setAge(30);
            person2.setMarried(true);
            client.savePerson(person2);
        } catch (TException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            transport.close();
        }
    }

}

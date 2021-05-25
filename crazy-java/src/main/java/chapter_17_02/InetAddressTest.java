package chapter_17_02;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * @author pengbo
 * @since 2021/2/24
 */
public class InetAddressTest {
    public static void main(String[] args) throws IOException {
        // InetAddress address = InetAddress.getByName("www.baidu.com");
        InetAddress address = InetAddress.getByName("localhost");

        System.out.println("是否可达：" + address.isReachable(3000));
        System.out.println("IP：" +address.getHostAddress());
    }
}

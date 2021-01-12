package com.westboy.example01;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author pengbo
 * @since 2021/1/12
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // System.out.println("msg:" + msg);
        // HttpObject msg 的信息如下：

        // 1. 使用浏览器进行访问 http://localhost:8899/
        // msg:DefaultHttpRequest(decodeResult: success, version: HTTP/1.1)
        // GET / HTTP/1.1
        // Host: localhost:8899
        // Connection: keep-alive
        // Cache-Control: max-age=0
        // sec-ch-ua: "Google Chrome";v="87", " Not;A Brand";v="99", "Chromium";v="87"
        // sec-ch-ua-mobile: ?0
        // Upgrade-Insecure-Requests: 1
        // User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36
        // Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
        // Sec-Fetch-Site: none
        // Sec-Fetch-Mode: navigate
        // Sec-Fetch-User: ?1
        // Sec-Fetch-Dest: document
        // Accept-Encoding: gzip, deflate, br
        // Accept-Language: zh-CN,zh;q=0.9,en;q=0.8
        // msg:EmptyLastHttpContent
        // msg:DefaultHttpRequest(decodeResult: success, version: HTTP/1.1)
        // GET /favicon.ico HTTP/1.1
        // Host: localhost:8899
        // Connection: keep-alive
        // Pragma: no-cache
        // Cache-Control: no-cache
        // sec-ch-ua: "Google Chrome";v="87", " Not;A Brand";v="99", "Chromium";v="87"
        // sec-ch-ua-mobile: ?0
        // User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36
        // Accept: image/avif,image/webp,image/apng,image/*,*/*;q=0.8
        // Sec-Fetch-Site: same-origin
        // Sec-Fetch-Mode: no-cors
        // Sec-Fetch-Dest: image
        // Referer: http://localhost:8899/
        // Accept-Encoding: gzip, deflate, br
        // Accept-Language: zh-CN,zh;q=0.9,en;q=0.8
        // msg:EmptyLastHttpContent


        // 2. 使用 curl "localhost:8899" 进行访问

        // 不加此判断时，使用 terminal 进行 curl "localhost:8899" 访问时会报错:
        // 一月 12, 2021 10:50:04 下午 io.netty.channel.DefaultChannelPipeline onUnhandledInboundException
        // 警告: An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception.
        // java.io.IOException: 远程主机强迫关闭了一个现有的连接。
        // 	at sun.nio.ch.SocketDispatcher.read0(Native Method)

        System.out.println("msg class: " + msg.getClass());
        System.out.println("remote address: " + ctx.channel().remoteAddress());

        // 休眠 60 秒，查看端口信息（同时可以查看 TCP 四次挥手过程）
        Thread.sleep(60 * 1000L);
        // Linux: lsof -i 8899
        // Windows: netstat -aon | findstr 8899
        // $ netstat -aon | findstr 8899
        //   TCP    0.0.0.0:8899           0.0.0.0:0              LISTENING       932
        //   TCP    [::]:8899              [::]:0                 LISTENING       932
        //   TCP    [::1]:8899             [::1]:65438            ESTABLISHED     932
        //   TCP    [::1]:65438            [::1]:8899             ESTABLISHED     3816

        // 客户端请求断开连接
        // $ netstat -aon | findstr 8899
        //   TCP    0.0.0.0:8899           0.0.0.0:0              LISTENING       932
        //   TCP    [::]:8899              [::]:0                 LISTENING       932
        //   TCP    [::1]:8899             [::1]:65438            CLOSE_WAIT      932   // 服务端收到客户端 FIN，响应 ACK 处于 CLOSE_WAIT
        //   TCP    [::1]:65438            [::1]:8899             FIN_WAIT_2      3816  // 客户端收到服务端 ACK，处于 FIN_WAIT_2，等待服务端断开连接

        // $ netstat -aon | findstr 8899
        //   TCP    0.0.0.0:8899           0.0.0.0:0              LISTENING       932
        //   TCP    [::]:8899              [::]:0                 LISTENING       932
        //   TCP    [::1]:65438            [::1]:8899             TIME_WAIT       0     // 客户端收到服务端 FIN，响应 ACK 后，处于 TIME_WAIT

        // $ netstat -aon | findstr 8899
        //   TCP    0.0.0.0:8899           0.0.0.0:0              LISTENING       932
        //   TCP    [::]:8899              [::]:0                 LISTENING       932

        // 加次判断之后不会报错
        if (msg instanceof HttpRequest) {

            HttpRequest httpMethod = (HttpRequest) msg;

            // curl -X GET "localhost:8899"
            // curl -X POST "localhost:8899"
            // curl -X PUT "localhost:8899"
            // curl -X DELETE "localhost:8899"
            System.out.println("请求方法名: " + httpMethod.method());

            URI uri = new URI(httpMethod.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求: favicon.ico");
                return;
            }

            JSONObject welcome = new JSONObject();
            welcome.putOnce("date", DateUtil.now());
            welcome.putOnce("message", "welcome to you!");

            ByteBuf content = Unpooled.copiedBuffer(welcome.toString(), CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);

            // 客户端响应结果：{"date":"2021-01-12 22:50:04","message":"welcome to you!"}
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel unregistered");
        super.channelUnregistered(ctx);
    }
}

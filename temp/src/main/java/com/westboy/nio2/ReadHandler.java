// package com.westboy.nio2;
//
// import java.nio.ByteBuffer;
// import java.nio.channels.CompletionHandler;
//
// /**
//  * @author westboy
//  * @since 2019/11/18
//  */
//
// public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
// 	//读取到消息后的处理
// 	@Override
// 	public void completed(Integer result, ByteBuffer attachment) {
// 		//attachment就是数据，调用flip操作，其实就是把读的位置移动最前面
// 		attachment.flip();
// 		//读取数据
//         // ...
// 	}
//
// 	@Override
// 	public void failed(Throwable exc, ByteBuffer attachment) {
//
// 	}
//
// 	// void failed(Throwable exc, A attachment){
//     //     ...
// 	// }
// }

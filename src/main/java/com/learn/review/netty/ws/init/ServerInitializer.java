package com.learn.review.netty.ws.init;

import com.learn.review.netty.ws.handler.TestTextWebSocketFrameHandler;
import com.learn.review.netty.ws.handler.WebSocketIdleHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new WebSocketIdleHandler(30, 30, 30));

        // HTTP协议的编码器和解码器
        pipeline.addLast(new HttpServerCodec());
        // 以块方式写的，添加ChunkedWriteHandler处理器
        pipeline.addLast(new ChunkedWriteHandler());

        // HTTP数据在传输过程中是分段的，HttpObjectAggregator可以聚合多个段
        pipeline.addLast(new HttpObjectAggregator(8192));

        pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

        pipeline.addLast(new TestTextWebSocketFrameHandler());
    }
}

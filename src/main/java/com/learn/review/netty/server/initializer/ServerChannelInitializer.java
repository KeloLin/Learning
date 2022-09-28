package com.learn.review.netty.server.initializer;

import com.learn.review.netty.server.handler.ServerBasicChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * @Author: Kelo
 * @Date: 2022/9/26
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ServerBasicChannelHandlerAdapter());
    }
}

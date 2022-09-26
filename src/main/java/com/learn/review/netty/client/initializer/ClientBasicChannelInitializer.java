package com.learn.review.netty.client.initializer;

import com.learn.review.netty.client.handller.ClientBasicChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author: Kelo
 * @Date: 2022/9/26
 */
public class ClientBasicChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ClientBasicChannelHandler());
    }
}

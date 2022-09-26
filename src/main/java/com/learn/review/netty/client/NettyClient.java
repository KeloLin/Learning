package com.learn.review.netty.client;

import com.learn.review.netty.client.initializer.ClientBasicChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: Kelo
 * @Date: 2022/9/23
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ClientBasicChannelInitializer());
        System.out.println("客户端准备好了。");
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6677).sync();
        channelFuture.channel().closeFuture().sync();

    }
}

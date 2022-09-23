package com.learn.review.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: Kelo
 * @Date: 2022/9/21
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 创建线程组，bossGroup处理客户端请求事件
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // workerGroup负责处理read/write事件
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 创建启动类
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 为其设置相关配置
        bootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(null);
                    }
                });

        // 绑定端口
        ChannelFuture channelFuture = bootstrap.bind(6677).sync();
        // 对通道的关闭事件进行监听
        channelFuture.channel().closeFuture().sync();
    }
}

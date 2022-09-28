package com.learn.review.netty.ws.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @Author: Kelo
 * @Date: 2022/9/27
 */
public class TestTextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("server receive: " + msg.text());
        ctx.writeAndFlush(new TextWebSocketFrame("server time: " + LocalDateTime.now()));
    }

    /**
     * web客户端连接后，触发方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("TestTextWebSocketFrameHandler - handlerAdded/asLongText：" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("TestTextWebSocketFrameHandler - handlerRemoved/asLongText：" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        System.out.println("TestTextWebSocketFrameHandler - exceptionCaught");
        ctx.close();
    }
}

package com.learn.review.netty.ws.handler;

import io.netty.handler.timeout.IdleStateHandler;

/**
 * @Author: Kelo
 * @Date: 2022/9/27
 */
public class WebSocketIdleHandler extends IdleStateHandler {

    /**
     * 多久没有读/写操作，就发送一个心跳检测包，检测是否处于连接状态
     *
     * @param readerIdleTimeSeconds 读空闲
     * @param writerIdleTimeSeconds 写空闲
     * @param allIdleTimeSeconds    读写空闲
     */
    public WebSocketIdleHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }

}

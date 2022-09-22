package com.learn.review.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: Kelo
 * @Date: 2022/9/22
 */
public class NioClient {

    private SocketChannel socketChannel;
    private Selector selector;

    public NioClient() throws IOException {
        // 打开socket channel
        socketChannel = SocketChannel.open();
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 打开selector
        selector = Selector.open();
        // 把channel注册到selector中
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        // 连接指定端口
        socketChannel.connect(new InetSocketAddress(6677));
        System.out.println("客户端准备好了。");
        handleKeys();
    }

    private void handleKeys() throws IOException {
        while (true) {
            int select = selector.select();
            if (select == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 接受连接就绪
                if (key.isConnectable()) {
                    handleConnect(key);
                }
                // 接受读就绪
                if (key.isReadable()) {
                    handleRead(key);
                }
                // 接受写就绪
                if (key.isWritable()) {
                    handleWrite(key);
                }
            }
            iterator.remove();
        }
    }

    private void handleConnect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        channel.register(selector, SelectionKey.OP_READ);
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(byteBuffer);
        if (read > 0) {
            System.out.println(new String(byteBuffer.array()));
        }
    }

    private void handleWrite(SelectionKey key) throws IOException {

    }

    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient();
    }
}

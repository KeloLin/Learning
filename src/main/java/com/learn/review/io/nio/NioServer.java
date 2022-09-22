package com.learn.review.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    public NioServer() throws IOException {
        // 打开服务端 socket channel
        serverSocketChannel = ServerSocketChannel.open();
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 绑定服务端端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6677));
        // 创建selector
        selector = Selector.open();
        // 将服务端的channel注册到selector中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动了。");
        listen();
    }

    private void listen() {
        try {
            // 监听selector
            while (true) {
                int select = selector.select(1000);
                if (select == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        System.out.println("有请求连接了。");
                        handleAccept();
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isWritable()) {
                        handleWrite(key);
                    }
                }
                iterator.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleWrite(SelectionKey key) throws IOException {

    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(byteBuffer);
        if (read > 0) {
            String msg = new String(byteBuffer.array());
            System.out.println(msg);
        }
    }

    private void handleAccept() throws IOException {
        // 建立连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 将socket channel注册到selector中，并设置为可读
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();

    }
}

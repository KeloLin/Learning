package com.learn.review.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Kelo
 * @Date: 2022/9/21
 */
public class BioServer {

    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = null;
        try {
            // 如果有客户端连接就创建一个线程与之连接
            serverSocket = new ServerSocket(6666);
            System.out.println("server started.");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("client connected.");
                threadPool.execute(() -> handler(socket));
            }
        } catch (IOException e) {

        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private static void handler(Socket socket) {

        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, bytes.length));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

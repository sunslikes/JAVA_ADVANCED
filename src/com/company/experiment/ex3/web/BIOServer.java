package com.company.experiment.ex3.web;


import com.company.experiment.ex3.web.ServerSocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    // 默认的端口号
    private static int DEFAULT_PORT = 8080;

    // 单例的ServerSocket
    private static ServerSocket serverSocket;

    // 根据传入参数设置监听端口，如果没有参数调用以下方法并使用默认值
    public static void start() throws IOException {
        // 使用默认值
        start(DEFAULT_PORT);
    }
    public synchronized static void start(int port) throws IOException {
        if (serverSocket != null) {
            return;
        }
        try {
            // 通过构造函数创建ServerSocket
            // 如果端口合法且空闲，服务端就监听成功
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端已启动，端口号：" + port);

            // 通过无限循环监听客户端连接
            // 如果没有客户端接入， 将阻塞在accept操作上
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerSocketHandler(socket)).start();
            }
        } finally {
            // 一些有必要的清理工作
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
                System.out.println("服务端已关闭");
            }
        }
    }
}

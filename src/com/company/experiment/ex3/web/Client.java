package com.company.experiment.ex3.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Client {
    // 服务端端口
    private static final int PORT = 8080;
    // 客户端数上限
    private static final int MAX = 10;
    // 多例模式
    private static List<Thread> threads = new ArrayList<>();

    static class In implements Callable<Boolean>{
        @Override
        public Boolean call() throws Exception {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if ("1".equals(line)) {
                return false;
            }
            return true;
        }
    }

    private static void newClient() {
        // 客户端数量过多，不操作
        if (Client.threads.size() >= Client.MAX) {
            return;
        }
        System.out.println("新建客户端，" + String.valueOf(Client.threads.size() + 1));
        // 新建一个客户端（线程）
        Thread thread = new Thread(new Runnable() {  // 匿名类实现
            @Override
            public void run() {
                try {
                    int MYNumber = Client.threads.size();
                    while (!Thread.currentThread().isInterrupted()) {  // 如果线程没有被停止
                        Socket socket = new Socket("172.16.27.164", PORT); // 建立连接
                        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // 输入流
                        PrintWriter printWriter = new PrintWriter(socket.getOutputStream()); // 输出流
                        String send = "I am " + MYNumber;
                        printWriter.println(send);
                        printWriter.flush();
                        String get = bufferedReader.readLine();
                        System.out.println("Server says: " + get);
                        bufferedReader.close(); // 输入流关闭
                        printWriter.close(); // 输出流关闭
                        socket.close(); // 连接关闭
                        try {
                            Thread.sleep(2000); // 线程阻塞两秒
                        } catch (InterruptedException e) {  // 如果线程在阻塞态被停止，则抛出异常，收尾处理

                            Thread.currentThread().interrupt(); // 线程停止
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Client.threads.add(thread);
        thread.start();
    }

    public static void start() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String line = null;
        int i = 0;
//        while (in.call()) {
        line = scanner.nextLine();
        List<Thread> threads = new ArrayList<>();
        while (!"1".equals(line)) {
            i ++;
            int finalI = i;
            int finalI1 = i;
            Client.newClient();
            line = scanner.nextLine();
        }
        for (Thread thread: Client.threads) {
//            thread.stop(); // 太暴力不建议使用
            thread.interrupt();
        }
        System.out.println("结束");
    }
}

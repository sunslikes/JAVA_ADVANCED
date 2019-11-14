package com.company.experiment.ex3.web;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("服务端");
                    BIOServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("客户端");
                try {
                    Client.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();
        client.start();
    }
}

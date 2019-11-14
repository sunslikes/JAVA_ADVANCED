package com.company.experiment.ex3.web;

import java.io.*;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {
    Socket socket;
    private ServerSocketHandler() {}
    public ServerSocketHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            PrintStream writer = new PrintStream(socket.getOutputStream());
            String client = "<" + socket.getInetAddress().toString() + ":" + socket.getPort() + ">";
            String request = new String();
            while (true) {
                if (socket.getInputStream().available() <= 0) {
                    break;
                }
                request = bufferedReader.readLine();
                if (request == null) {
                    break;
                }
                System.out.println(client + "says:" + request);
                String response =  "<" + socket.getLocalAddress().toString() + ":" + socket.getLocalPort() + ">" + "accept!";
                System.out.println(response);
                writer.println(response);
                writer.flush();
            }
            writer.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

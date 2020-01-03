package com.clown.netty.nio.biosocket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket =  new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8899));
        while(true) {
           //会阻塞在这里等待有新的客户端连接进来
           Socket socket = serverSocket.accept();
           //每个客户端连接进来都会启动一个新的线程
           new Thread(new Handler(socket)).start();
        }
    }
}

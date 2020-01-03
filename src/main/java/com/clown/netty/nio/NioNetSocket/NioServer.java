package com.clown.netty.nio.NioNetSocket;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NioServer {
    public static void main(String[] args) throws Exception{
        int[] ports = new int[5];

        Selector open = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8899));
    }
}

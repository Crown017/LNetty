package com.clown.netty.nio.niosocket3;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

public class NioServer {
    private static HashMap<String,SocketChannel> hashMap = new HashMap<>();

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        //就是注册一个关注ACCEPT事件的Channel
        //关联 selector ---> SelectionKey ---> Channel
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        while (true){
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if (next.isAcceptable()){
                    ServerSocketChannel serverChannel = (ServerSocketChannel) next.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    //在选择器上注册一个SocketChannel 关注 OP_READ事件
                    socketChannel.register(selector,SelectionKey.OP_READ);

                    String key = UUID.randomUUID().toString();

                    hashMap.put(key,socketChannel);
                    iterator.remove();
                }else if (next.isReadable()){
                    SocketChannel channel =  (SocketChannel) next.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int read = channel.read(byteBuffer);

                    if (read > 0 ) {
                        byteBuffer.flip();
                        Charset charset = Charset.forName("utf-8");
                        String protomessage  = String.valueOf(charset.decode(byteBuffer).array());
                        String message = "收到来自客户端:"+channel.getRemoteAddress()+"的信息:"+protomessage ;
                        System.out.println(message);

                        String sendKey = null ;
                        for (Map.Entry<String,SocketChannel> entry :hashMap.entrySet()){
                            if (entry.getValue() == channel){
                                sendKey = entry.getKey();
                            }
                        }

                        for (Map.Entry<String,SocketChannel> entry :hashMap.entrySet()){
                            if (entry.getValue() == channel){
                                sendKey = entry.getKey();
                            }
                        }




                    }
                    iterator.remove();
                }
            }
        }
    }
}

package com.clown.netty.nio.NioNetSocket;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class PlainNioServer {
    public void server(int port) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(port));

        //打开选择器来处理Channel
        Selector selector = Selector.open();
        //会产生一个SelectionKey,并且SelectionKey会添加到选择器的键集中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer buffer = ByteBuffer.wrap("Hi !\r\n".getBytes());

        for (;;){
            try {
                //将会阻塞等待下一个事件的传入
                int select = selector.select();
            }catch (IOException e){
                e.printStackTrace();
                break;
            }
            //说明有新的事件发生了
            //获取所有接受事件SelectionKey实例
            Set<SelectionKey> readkey = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readkey.iterator();
            while (iterator.hasNext()){
                SelectionKey currentKey = iterator.next();
                iterator.remove();
                try {
                    //检查事件是否是一个新的已经就绪可以被接受的连接
                    if (currentKey.isAcceptable()){

                    }
                    //检查套接字是否已经准备好写数据
                    if (currentKey.isWritable()){

                    }
                    //
                    if (currentKey.isReadable()){

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

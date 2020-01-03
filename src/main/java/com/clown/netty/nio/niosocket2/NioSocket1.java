package com.clown.netty.nio.niosocket2;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioSocket1 {
    public static void main(String[] args) throws Exception{
        int [] prots = new int[5];
        prots[0] = 5000;
        prots[1] = 5001;
        prots[2] = 5002;
        prots[3] = 5003;
        prots[4] = 5004;

        Selector selector = Selector.open();

        for (int i =  0 ; i < prots.length ;i++ ){
            ServerSocketChannel serverSocketChannel =  ServerSocketChannel.open();
            //非阻塞的方式 很重要
            serverSocketChannel.configureBlocking(false);
            //返回与channel关联的ServerSocket对象
            ServerSocket serverSocket =  serverSocketChannel.socket();
            //绑定
            serverSocket.bind(new InetSocketAddress(prots[i]));
            //注册 对什么样的Key 是感兴趣的
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }


        while (true){
            int select = selector.select();
            System.out.println("num of key"+ select);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selctionkeys"+ selectionKeys);
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                //一个SelectionKey 关联与之对应的Channel
                SelectionKey next = iterator.next();

                if (next.isAcceptable()){
                    SelectableChannel channel = next.channel();
                    if (channel instanceof  ServerSocketChannel){
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)channel;
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);

                        socketChannel.register(selector,SelectionKey.OP_READ);
                        //一定要删除掉  连接已经被创建了
                        iterator.remove();
                        System.out.println("客户端链接已经创建"+ socketChannel.getRemoteAddress());
                    }
                }else if (next.isReadable()){
                    SocketChannel socketChannel =(SocketChannel)next.channel();
                    int byteRead = 0 ;

                    while (true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read < 0){
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        byteRead += read ;
                    }
                    System.out.println("读取了"+ byteRead+ "from" +socketChannel.getRemoteAddress());
                    //别忘了去掉
                    iterator.remove();
                }

            }
        }
    }
}

package com.clown.netty.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultipexerTimeServer  implements Runnable{

    private Selector selector;

    private ServerSocketChannel socketChannel;

    private boolean stop;

    public  MultipexerTimeServer(int port){
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(port),1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void setStop(){
        this.stop = false ;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator =selectionKeys.iterator();
                SelectionKey selectionKey = null;
                while (iterator.hasNext()){
                    selectionKey = iterator.next();
                    iterator.remove();
                    try{
                        handleInput(selectionKey);
                    }catch (Exception e){
                        if (selectionKey != null){
                            selectionKey.cancel();
                            if (selectionKey.channel() != null){
                                selectionKey.channel().close();
                            }
                        }
                        e.printStackTrace();
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        if (selector != null){
            try {
                selector.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey selectionKey) throws  Exception {
        if (selectionKey.isValid()){
            if (selectionKey.isAcceptable()){
               //接受新的连接
               ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel= serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ);
            }

            if (selectionKey.isReadable()){
                //读数据
                SocketChannel socketChannel =(SocketChannel)selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0){
                    byteBuffer.flip();
                    byte[]  bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String info = new String(bytes,"UTF-8");
                    System.out.println("server receive{"+info+"}");
                    String time = new Date(System.currentTimeMillis()).toString();
                    doWrite(socketChannel,time);

                }

            }
        }
    }

    private void doWrite(SocketChannel socketChannel, String response) throws IOException{
        if (response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer byteBuffer= ByteBuffer.allocate(1024);
            byteBuffer.put(bytes);
            socketChannel.write(byteBuffer);
        }
    }
}

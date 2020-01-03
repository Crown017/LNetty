package com.clown.netty.nio.basic.reactor.design;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {

    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);

    static final int reading = 0, sending = 1;

    public Handler(SocketChannel socket, Selector selector) throws IOException {
       this.socket = socket ;
       socket.configureBlocking(false);
       sk  = socket.register(selector,0);
       sk.attach(this);
       sk.interestOps(SelectionKey.OP_READ);
       selector.wakeup();
    }

    @Override
    public void run() {

    }
}

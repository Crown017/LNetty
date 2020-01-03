package com.clown.netty.nio.socket;

public class TimeServer {
    public static void main(String[] args) {

        MultipexerTimeServer server = new MultipexerTimeServer(8899);
        Thread thread = new Thread(server);
        thread.start();
    }
}

package com.clown.netty.nio.NioNetSocket;

import com.sun.org.apache.xpath.internal.operations.String;
import sun.nio.ch.ThreadPool;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PlainOioServer {

    private static Logger logger = LogManager.getLogManager().getLogger(PlainOioServer.class.getName());
    private static ExecutorService executorService = Executors.newFixedThreadPool(20);
    public void server(int port) throws IOException{
        final ServerSocket serverSocket = new ServerSocket(port);
        try {
            for (;;){
                final Socket  socket = serverSocket.accept();
                logger.info("Accepted connection  from "+ socket.getInetAddress());
                Handler handler = new Handler(socket);
                executorService.submit(handler);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    class Handler implements Runnable{

        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run(){
            try( OutputStream outputStream  = socket.getOutputStream();) {
                outputStream.write("hell welcome".getBytes());
                outputStream.flush();
                socket.close();
            }catch (IOException e){
                e.printStackTrace();

            }finally {
            }
        }
    }
}

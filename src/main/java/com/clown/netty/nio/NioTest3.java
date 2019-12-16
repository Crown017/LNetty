package com.clown.netty.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {
    public static void main(String[] args) {
        try(FileOutputStream fileOutputStream = new FileOutputStream("NioTest2.txt");
            FileChannel channel = fileOutputStream.getChannel()){
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            String data = "Hello world ";
            byte[] buffer = data.getBytes();
            for (int i = 0; i< buffer.length; i++){
                byteBuffer.put(buffer[i]);
            }

            byteBuffer.flip();

            channel.write(byteBuffer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

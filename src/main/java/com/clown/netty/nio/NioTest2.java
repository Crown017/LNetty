package com.clown.netty.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {
    public static void main(String[] args) {

        try(FileInputStream fileInputStream = new FileInputStream("NioTest.txt");
            FileChannel channel = fileInputStream.getChannel()){
                ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                channel.read(byteBuffer);
                byteBuffer.flip();
                while (byteBuffer.remaining() > 0){
                    System.out.print((char) byteBuffer.get());
                }
                System.out.println();
        }catch (IOException e){
                e.printStackTrace();
        }
    }
}

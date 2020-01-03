package com.clown.netty.nio;

import java.nio.ByteBuffer;

public class NioTest4 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(16);
    }
}

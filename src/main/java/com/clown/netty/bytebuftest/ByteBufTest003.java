package com.clown.netty.bytebuftest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class ByteBufTest003 {
    public static void main(String[] args) {
        ByteBuf byteBuf  = Unpooled.copiedBuffer("ABCDEFH", CharsetUtil.UTF_8);

        //可读的字节
        while (byteBuf.isReadable()){
            System.out.println((char) byteBuf.readByte() + " ====== " +byteBuf.readerIndex());
        }

        //已经读完了再读就会发生异常
        byteBuf.readByte();
    }
}

package com.clown.netty.bytebuftest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;


/**
 * get set 不会改变index
 *
 */
public class ByteBufTest002 {
    public static void main(String[] args) {
        ByteBuf byteBuf  = Unpooled.copiedBuffer("ABCDEFH", CharsetUtil.UTF_8);
        System.out.println(byteBuf.writerIndex());
        for (int i  = 0; i < byteBuf.writerIndex() ; i++){
            byte b = byteBuf.getByte(i);
            System.out.println((char)b + "---------" + byteBuf.readerIndex());
        }
        System.out.println();

        for (int i = 0 ; i < byteBuf.writerIndex(); i++){
            byteBuf.setByte(1,'l');
            System.out.println(byteBuf.writerIndex());
        }
    }
}

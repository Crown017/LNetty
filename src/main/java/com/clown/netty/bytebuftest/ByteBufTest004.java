package com.clown.netty.bytebuftest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.security.SecureRandom;


/**
 * 可写字节
 */
public class ByteBufTest004 {
    public static void main(String[] args) {
        ByteBuf byteBuf  = Unpooled.copiedBuffer("ABCDEFH", CharsetUtil.UTF_8);
        SecureRandom random = new SecureRandom();
        System.out.println("capacity is" + byteBuf.capacity());
        while (byteBuf.writableBytes() > 0 ){
            byteBuf.writeByte(random.nextInt());
            System.out.println(byteBuf.writerIndex());
        }
    }
}

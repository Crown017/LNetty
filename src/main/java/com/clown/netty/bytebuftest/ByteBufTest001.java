package com.clown.netty.bytebuftest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;



public class ByteBufTest001 {

    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);

        CharSequence charSequence = buf.getCharSequence(0,11, CharsetUtil.UTF_8);

        buf.writeChar((int) 'l');

        System.out.println(charSequence);

        System.out.println(buf.writerIndex());
    }

}

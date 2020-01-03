package com.clown.netty.myprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 协议解码器
 *
 */
public class MyProtocolDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //所要读取的数据的长度
        int length = in.readInt();

        byte[] buffer = new byte[length];
        //
        in.readBytes(buffer);

        MyProtocol protocol = new MyProtocol();
        protocol.setLength(length);
        protocol.setContent(buffer);

        out.add(protocol);
    }
}

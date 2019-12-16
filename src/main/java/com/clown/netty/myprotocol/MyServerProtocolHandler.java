package com.clown.netty.myprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerProtocolHandler extends SimpleChannelInboundHandler<MyProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocol msg) throws Exception {
        System.out.println("接受到客户端发送的消息"+ ctx.channel().remoteAddress() + "》》》"+ new String(msg.getContent(), Charset.forName("utf-8")));

        //返回给客户端一个UUID
        MyProtocol myProtocol = new MyProtocol();
        String info = UUID.randomUUID().toString();
        byte[] buffer = info.getBytes();
        myProtocol.setContent(buffer);
        myProtocol.setLength(buffer.length);
        //封装成一个协议，会有出站处理器来处理
        ctx.writeAndFlush(myProtocol);
    }
}

package com.clown.netty.myprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class MyProtocolCilentHandler extends SimpleChannelInboundHandler<MyProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyProtocol msg) throws Exception {
        System.out.println("接受到服务端发送的消息"+ ctx.channel().remoteAddress() + "》》》"+ new String(msg.getContent(), Charset.forName("utf-8")));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
         MyProtocol myProtocol = new MyProtocol();
         String message = "这是来自客户端的问候";
         byte[] bytes= message.getBytes();
         myProtocol.setContent(bytes);
         myProtocol.setLength(bytes.length);

         ctx.writeAndFlush(myProtocol);
    }
}

package com.clown.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {

        ByteBufAllocator allocator = channelHandlerContext.alloc();
        ByteBuf buffer = allocator.buffer(16, 32);


        ByteBufAllocator allocator1 =  channelHandlerContext.channel().alloc();
        ByteBuf byteBuf2 = allocator1.buffer(32);

        ByteBuf directByteBuf = allocator1.directBuffer(16);





        System.out.println(msg.getClass());
        if ( msg instanceof HttpRequest ) {
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);

            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, content);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            channelHandlerContext.writeAndFlush(httpResponse);
        }
    }
}

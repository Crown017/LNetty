package com.clown.netty.myprotocol;

import com.clown.netty.codec.MyClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyProtocolDecoder());
        pipeline.addLast(new MyProtocolEncoder());
        pipeline.addLast(new MyProtocolCilentHandler());
    }
}

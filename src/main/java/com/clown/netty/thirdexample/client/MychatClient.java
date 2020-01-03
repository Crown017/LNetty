package com.clown.netty.thirdexample.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class MychatClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 8899)).sync();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            Channel channel = future.channel();

            for (;;){
                channel.writeAndFlush(bufferedReader.readLine()+"\r\n");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }


    }
}

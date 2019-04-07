package com.rain.study.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {

    private int port;

    public HelloServer(int port) {
        this.port = port;
    }


    public void run() throws Exception {

    // 用来接收进来的连接
    EventLoopGroup bossGroup = new NioEventLoopGroup();

    // 用来接受要处理的连接
    EventLoopGroup workFroup = new NioEventLoopGroup();

    System.out.println(" port is :" + port);

    try{

        ServerBootstrap s  = new ServerBootstrap();
        // 组装NioEventLoopGroup
        s.group(bossGroup,workFroup)
                // 设置channel类型为NIO类型
                .channel(NioServerSocketChannel.class) // 这里告诉Channel如何接收新的连接
                .childHandler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new HelloServerHandler());
                    }
                })
                // 设置连接配置参数
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true);

        // 绑定端口，开始接收进来的连接
        ChannelFuture f = s.bind(port).sync();
        // 等待服务器socket关闭
        f.channel().closeFuture().sync();

    }catch (Exception e){
        workFroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
    }

    }

  public static void main(String[] args) {
      int port = 10110;

      try {
          new HelloServer(port).run();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
